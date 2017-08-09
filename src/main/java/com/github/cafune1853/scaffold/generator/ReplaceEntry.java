package com.github.cafune1853.scaffold.generator;

/**
 * Created by huangzhw on 2017/7/24.
 */

public class ReplaceEntry {
	private final String key;
	private final String replaceValue;
	
	public ReplaceEntry(String key, String replaceValue){
		this.key = key;
		this.replaceValue = replaceValue;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getReplaceValue() {
		return replaceValue;
	}
}
