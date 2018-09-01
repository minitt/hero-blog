package net.minitt.hero.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import net.minitt.hero.core.dao.ResourceDao;
import net.minitt.hero.core.entity.Resource;

//@Component
public class HeroInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource,InitializingBean {
	
	@Autowired
	private ResourceDao resourceDao;
	
	private HashMap<String, Collection<ConfigAttribute>> map = null;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		loadResourceDefine();
	}
	
	/**
	 * 加载所有资源权限
	 */
	public void loadResourceDefine(){
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        List<Resource> resources = resourceDao.findAll();
        for(Resource resource : resources) {
        	if(resource.getAuth()!=null) {
        		array = new ArrayList<>();
                //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
                array.add(new SecurityConfig(resource.getAuth()));
                //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
                map.put("/admin"+resource.getPath(), array);
        	}
        }
    }

	/**
	 * 访问地址对应权限
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String url = ((FilterInvocation) object).getRequestUrl();
        System.out.println(url);
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
//            if(matcher.matches(request)) {
                return map.get(resUrl);
//            }
        }
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
