package com.github.cafune1853.scaffold.generator;

import com.github.cafune1853.scaffold.util.FileUtil;
import com.github.cafune1853.scaffold.util.StringUtil;

import java.io.File;
import java.util.List;

/**
 * @author doggy
 * Created on 2017-08-08.
 */
public class PackageGenerator {
    public static final String POM_NAME = "pom.xml";
    public static final String OUT_PATH = FileUtil.class.getClassLoader().getResource("").getFile();
    private static final String RAW_GROUP_ID = "com.doggy.groupId";
    private static final String RAW_ARTIFACT_ID = "doggy-module";
    private final String groupId;
    private final String artifactId;
    private final String artifactPath;
    private final String rawPath;
    private final boolean isParent;
    private final List<ReplaceEntry> replaceEntryList;

    public PackageGenerator(String groupId, String artifactId, String suffix, boolean isParent, List<ReplaceEntry> replaceEntryList) {
        this.isParent = isParent;
        this.groupId = groupId == null ? "com.github.cafune1853" : groupId;
        this.replaceEntryList = replaceEntryList;
        if (suffix != null) {
            this.artifactId = artifactId + "-" + suffix;
            this.artifactPath = OUT_PATH + artifactId + File.separator + this.artifactId + File.separator;
            this.rawPath = OUT_PATH + RAW_ARTIFACT_ID + File.separator + RAW_ARTIFACT_ID + "-" + suffix + File.separator;
        } else {
            this.artifactId = artifactId;
            this.artifactPath = OUT_PATH + artifactId + File.separator;
            this.rawPath = OUT_PATH + RAW_ARTIFACT_ID + File.separator;
        }
    }

    public final void build() {
        FileUtil.mkdirs(artifactPath);
        //cp pom
        FileUtil.cp(rawPath + POM_NAME, artifactPath + POM_NAME);
        if (!isParent) {
            FileUtil.create(getMainJavaPath(), ".gitkeeper");
            FileUtil.create(getMainResourcesPath(), ".gitkeeper");
            FileUtil.create(getTestJavaPath(), ".gitkeeper");
            FileUtil.create(getTestResourcePath(), ".gitkeeper");
        }
        copyPackages();
        copyResources();
        copyTestPackages();
        copyTestResources();
        copyWebapp();
        replaceFiles();
    }

    private void replaceFiles() {
        FileUtil.replaceAllFiles(artifactPath, replaceEntryList);
    }

    private void copyPackages() {
        FileUtil.cpAll(getRawJavaPath(), getMainJavaPath());
    }

    private void copyResources() {
        FileUtil.cpAll(getRawResourcesPath(), getMainResourcesPath());
    }

    private void copyTestPackages() {
        FileUtil.cpAll(getRawTestJavaPath(), getTestJavaPath());
    }

    private void copyTestResources() {
        FileUtil.cpAll(getRawTestResourcesPath(), getTestResourcePath());
    }

    private void copyWebapp() {
        FileUtil.cpAll(getRawWebappPath(), getWebAppPath());
    }

    private String getRawJavaPath() {
        return rawPath + "src" + File.separator + "main" + File.separator + "java" + File.separator + StringUtil.getPackagePath(RAW_GROUP_ID, RAW_ARTIFACT_ID, File.separator) + File.separator;
    }

    private String getRawResourcesPath() {
        return rawPath + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    }

    private String getRawTestJavaPath() {
        return rawPath + "src" + File.separator + "test" + File.separator + "java" + File.separator + StringUtil.getPackagePath(RAW_GROUP_ID, RAW_ARTIFACT_ID, File.separator) + File.separator;
    }

    private String getRawTestResourcesPath() {
        return rawPath + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    }

    private String getRawWebappPath() {
        return rawPath + "src" + File.separator + "main" + File.separator + "webapp" + File.separator;
    }

    private String getTestResourcePath() {
        return this.artifactPath + "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    }

    private String getMainResourcesPath() {
        return this.artifactPath + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    }

    private String getWebAppPath() {
        return this.artifactPath + "src" + File.separator + "main" + File.separator + "webapp" + File.separator;
    }

    private String getTestJavaPath() {
        return this.artifactPath + "src" + File.separator + "test" + File.separator + "java" + File.separator + StringUtil.getPackagePath(groupId, artifactId, File.separator) + File.separator;
    }

    private String getMainJavaPath() {
        return this.artifactPath + "src" + File.separator + "main" + File.separator + "java" + File.separator + StringUtil.getPackagePath(groupId, artifactId, File.separator) + File.separator;
    }

}
