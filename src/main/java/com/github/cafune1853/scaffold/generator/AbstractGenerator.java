package com.github.cafune1853.scaffold.generator;

import com.github.cafune1853.scaffold.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author doggy
 * Created on 2017-08-08.
 */
public abstract class AbstractGenerator implements Generator {
    public static final String POM_NAME = "pom.xml";
    public static final String OUT_PATH = FileUtil.class.getClassLoader().getResource("").getFile();
    private final String groupId;
    private final String artifactId;
    private final String artifactPath;
    private final String resourcePath;
    private final boolean needCode;

    public AbstractGenerator(String groupId, String artifactId, String suffix, boolean needCode) {
        this.needCode = needCode;
        this.groupId = groupId == null ? "com.github.cafune1853" : groupId;
        if (suffix != null) {
            this.artifactId = artifactId + "-" + suffix;
            this.artifactPath = OUT_PATH + artifactId + File.separator + this.artifactId + File.separator;
            this.resourcePath = OUT_PATH + "raw" + File.separator + suffix + File.separator;
        } else {
            this.artifactId = artifactId;
            this.artifactPath = OUT_PATH + artifactId;
            this.resourcePath = OUT_PATH + "raw" + File.separator;
        }
    }

    @Override
    public final void build() {
        FileUtil.mkdirs(artifactPath);
        //cp pom
        FileUtil.cp(resourcePath + File.separator + POM_NAME, artifactPath + File.separator + POM_NAME);
        if (needCode) {
            FileUtil.create(getMainJavaPath(), ".gitkeeper");
            FileUtil.create(getMainResourcesPath(), ".gitkeeper");
            FileUtil.create(getTestJavaPath(), ".gitkeeper");
            FileUtil.create(getTestResourcePath(), ".gitkeeper");
        }
        setFiles();
    }

    @Override
    public abstract void setFiles();

    protected final String getTestResourcePath() {
        return this.artifactPath + "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    }

    protected final String getMainResourcesPath() {
        return this.artifactPath + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    }

    protected final String getTestJavaPath() {
        return this.artifactPath + "src" + File.separator + "test" + File.separator + "java" + File.separator + getPackagePath(File.separator) + File.separator;
    }

    protected final String getMainJavaPath() {
        return this.artifactPath + "src" + File.separator + "main" + File.separator + "java" + File.separator + getPackagePath(File.separator) + File.separator;
    }

    //will return like com/github/cafune1853/spring/test
    protected final String getPackagePath(String separator) {
        List<String> packages = new ArrayList<>();
        String[] gs = groupId.split("\\.");
        packages.addAll(Arrays.asList(gs));
        String[] as = artifactId.split("-");
        packages.addAll(Arrays.asList(as));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < packages.size() - 2; i++) {
            stringBuilder.append(packages.get(i));
            stringBuilder.append(separator);
        }
        stringBuilder.append(packages.get(packages.size() - 1));
        return stringBuilder.toString();
    }


}
