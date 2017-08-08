package com.github.cafune1853.scaffold.util;

/**
 * Created by huangzhw on 2017/7/24.
 */

class ReplaceEntry {
	private final String key;
	private final String replaceValue;
	
	public ReplaceEntry(String key, String replaceValue){
		this.key = key;
		this.replaceValue = replaceValue;
	}
	
	String getKey() {
		return key;
	}
	
	String getReplaceValue() {
		return replaceValue;
	}
}
