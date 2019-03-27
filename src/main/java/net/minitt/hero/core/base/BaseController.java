package net.minitt.hero.core.base;

import org.springframework.security.core.context.SecurityContextHolder;

import net.minitt.hero.core.security.SecurityUser;

public abstract class BaseController {
	
	private static final int SUCCESS_CODE = 20000;
	private static final int FAILD_CODE = 50000;
	
	protected <V> ResultJson<V> renderJson(V p) {
		ResultJson<V> m = new ResultJson<V>();
		m.setCode(SUCCESS_CODE);
		m.setData(p);
		return m;
	}
	
	protected ResultJson<String> renderSuceess(){
		return renderSuceess("OK");
	}
	
	protected ResultJson<String> renderSuceess(String msg){
		ResultJson<String> m = new ResultJson<String>();
		m.setCode(SUCCESS_CODE);
		m.setData(msg);
		return this.<String>renderJson(msg);
	}
	
	protected ResultJson<String> renderfail(){
		return renderfail("FAIL");
	}
	
	protected ResultJson<String> renderfail(String msg){
		ResultJson<String> m = new ResultJson<String>();
		m.setCode(FAILD_CODE);
		m.setData(msg);
		return this.<String>renderJson(msg);
	}
	
	protected SecurityUser getCurrUser() {
		return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
