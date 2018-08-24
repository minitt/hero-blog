package net.minitt.hero.core.base;

public abstract class BaseFrontController {
	protected String render(String path) {
		return "default"+"/"+path;
	}
}
