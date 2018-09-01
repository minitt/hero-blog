package net.minitt.hero.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.ExpressionBasedAnnotationAttributeFactory;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import net.minitt.hero.core.security.HeroPrePostAnnotationSecurityMetadataSource;

//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)// @PreAuthorize控制权限注解
public class SecurityMethodConfig extends GlobalMethodSecurityConfiguration {
	/**
     * 当前版本主要使用了ProtectPointcutPostProcessor实现Aop AspectJ的表达式方式的权限控制
     * 其原理是Spring没创建一个bean都要通过一组Aop的遍历，如果符合Aop表达式，则将这个bean的方法
     * 注册到MethodSecurityMetadataSource中。当调用某bean的方法时，则检查该方法是否已经在注册
     * 列表中，如果存在则检查是否具有对应权限。
     *
     * 但是使用Java Configuration时出现了问题
     * 1.ProtectPointcutPostProcessor不能被访问、被继承
     * 2.可以通过代码复制或者反射实例化该对象
     * 3.如果将其发布成Spring Bean，并按照xml的方式配置属性，运行时会报错，尚未研究报错具体内容
     *
     * @return
     */
    @Override
    protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
    	ExpressionBasedAnnotationAttributeFactory attributeFactory = new ExpressionBasedAnnotationAttributeFactory(
				getExpressionHandler());
    	HeroPrePostAnnotationSecurityMetadataSource metadataSource = new HeroPrePostAnnotationSecurityMetadataSource(attributeFactory);
        return metadataSource;
    }

	@Override
	public void setMethodSecurityExpressionHandler(List<MethodSecurityExpressionHandler> handlers) {
		// TODO Auto-generated method stub
		super.setMethodSecurityExpressionHandler(handlers);
	}
    
    
}
