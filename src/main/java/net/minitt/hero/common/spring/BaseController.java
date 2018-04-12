package net.minitt.hero.common.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;

public class BaseController {
	
	private static final int SUCCESS_CODE = 20000;
	private static final int FAILD_CODE = 50000;
	
	protected Map<String,Object> renderJson(Page<?> p) {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("data", p);
		m.put("code", SUCCESS_CODE);
		return m;
	}
	
	protected Map<String,Object> renderJson(List<?> p) {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("data", p);
		m.put("code", SUCCESS_CODE);
		return m;
	}
	
	protected Map<String,Object> renderJson(Optional<?> p) {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("data", p);
		m.put("code", SUCCESS_CODE);
		return m;
	}
	
	protected Map<String,Object> renderSuceess(){
		return renderSuceess("OK");
	}
	
	protected Map<String,Object> renderSuceess(String msg){
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("data", msg);
		m.put("code", SUCCESS_CODE);
		return m;
	}
	
	protected Map<String,Object> renderfail(){
		return renderSuceess("FAIL");
	}
	
	protected Map<String,Object> renderfail(String msg){
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("data", msg);
		m.put("code", FAILD_CODE);
		return m;
	}
}
