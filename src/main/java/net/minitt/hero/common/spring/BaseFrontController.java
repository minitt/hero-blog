package net.minitt.hero.common.spring;

public abstract class BaseFrontController {
	protected String render(String path) {
		return "default"+"/"+path;
	}
}
