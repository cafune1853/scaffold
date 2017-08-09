package com.github.cafune1853.scaffold.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author doggy
 * Created on 2017-08-09.
 */
public class StringUtil {
    //will return like com/github/cafune1853/spring/test
    public static String getPackagePath(String groupId, String artifactId, String separator) {
        List<String> packages = new ArrayList<>();
        String[] gs = groupId.split("\\.");
        packages.addAll(Arrays.asList(gs));
        String[] as = artifactId.split("-");
        packages.addAll(Arrays.asList(as));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < packages.size() - 1; i++) {
            stringBuilder.append(packages.get(i));
            stringBuilder.append(separator);
        }
        stringBuilder.append(packages.get(packages.size() - 1));
        return stringBuilder.toString();
    }
}
