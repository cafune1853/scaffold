package com.github.cafune1853.scaffold;

import com.github.cafune1853.scaffold.generator.ModuleGenerator;
import com.github.cafune1853.scaffold.generator.ReplaceEntry;
import com.github.cafune1853.scaffold.util.StringUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by huangzhw on 2017/7/24.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        String groupId = "com.doggy.groupId.x";
        String artifactId = "doggy-modulex-y";
        String basePackages = StringUtil.getPackagePath(groupId, artifactId, ".");
        List<ReplaceEntry> replaceEntryList = Arrays.asList(
                new ReplaceEntry("com.doggy.groupId.doggy.module", basePackages),
                new ReplaceEntry("doggy-module", artifactId),
                new ReplaceEntry("com.doggy.groupId", groupId)
        );
        ModuleGenerator parentGenerator = new ModuleGenerator(groupId, artifactId, null, true, replaceEntryList);
        parentGenerator.build();
        ModuleGenerator commonGenerator = new ModuleGenerator(groupId, artifactId, "common", false, replaceEntryList);
        commonGenerator.build();
        ModuleGenerator apiGenerator = new ModuleGenerator(groupId, artifactId, "api", false, replaceEntryList);
        apiGenerator.build();
        ModuleGenerator providerGenerator = new ModuleGenerator(groupId, artifactId, "provider", false, replaceEntryList);
        providerGenerator.build();
        ModuleGenerator webGenerator = new ModuleGenerator(groupId, artifactId, "web", false, replaceEntryList);
        webGenerator.build();
    }
}
