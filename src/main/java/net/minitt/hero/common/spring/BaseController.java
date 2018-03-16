package net.minitt.hero.common.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;

public class BaseController {
	
	private static final int SUCCESS_CODE = 20000;
	private static final int FAILD_CODE = 50000;
	
	protected Map<String,Object> renderPage(Page<?> p) {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("data", p);
		m.put("code", SUCCESS_CODE);
		return m;
	}
}
