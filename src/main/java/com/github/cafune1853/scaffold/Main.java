package com.github.cafune1853.scaffold;

import com.github.cafune1853.scaffold.generator.*;

/**
 * Created by huangzhw on 2017/7/24.
 */

public class Main {
	public static void main(String[] args) throws Exception{
		String groupId = "com.github.cafune176";
		String artifactId = "test-spring";
		ParentGenerator parentGenerator = new ParentGenerator(groupId, artifactId);
		parentGenerator.build();
		ApiGenerator apiGenerator = new ApiGenerator(groupId, artifactId);
		apiGenerator.build();
		CommonGenerator commonGenerator = new CommonGenerator(groupId, artifactId);
		commonGenerator.build();
		ProviderGenerator providerGenerator = new ProviderGenerator(groupId, artifactId);
		providerGenerator.build();
		WebGenerator webGenerator = new WebGenerator(groupId, artifactId);
		webGenerator.build();
	}
}
