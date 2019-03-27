package net.minitt.hero.core.base;

public class ResultJson<V> {
	private int code;
	private V data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public V getData() {
		return data;
	}
	public void setData(V data) {
		this.data = data;
	}
}
