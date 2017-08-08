package com.github.cafune1853.scaffold.util;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 * Created by huangzhw on 2017/7/24.
 */

public class FileUtil {

    public static void mkdirs(final String dirs) {
        File file = new File(dirs);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void create(final String dirs, String fileName) {
        mkdirs(dirs);
        File file = new File(dirs + fileName);
        try {
            file.createNewFile();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void cp(final String srcFilePath, final String destFilePath) {
        try (Scanner in = new Scanner(new File(srcFilePath)); PrintWriter out = new PrintWriter(destFilePath)) {
            while (in.hasNextLine()) {
                out.println(in.nextLine());
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void mkdirsAndCreateNewFile(final String dirs, final String filePath) throws IOException {
        final String dirsPath = dirs.endsWith(File.separator) ? dirs : dirs + File.separator;
        final String fileName = filePath.replace(File.separator, "");
        File directory = new File(dirsPath);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                System.out.println("Create dirs:" + dirs + " fail");
            }
        }
        File file = new File(dirsPath + fileName);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                System.out.println("Create file:" + dirsPath + fileName + " fail");
            }
        }
    }

    /**
     * 从srcFilePath读入所有文本并将对应到replaceEntry的内容替换，最终写入到destFilePath中。
     *
     * @param srcFilePath:    source file absolutely path
     * @param destFilePath:   destination file absolutely path
     * @param replaceEntries:
     * @throws IOException
     * @see ReplaceEntry
     */
    public static void replaceToNewFile(String srcFilePath, String destFilePath, List<ReplaceEntry> replaceEntries) throws IOException {
        try (Scanner in = new Scanner(new File(srcFilePath)); PrintWriter out = new PrintWriter(destFilePath)) {
            while (in.hasNextLine()) {
                String resolved = handlerReplacement(in.nextLine(), replaceEntries);
                out.println(resolved);
            }
        }
    }

    public static String getOutPath() {
        return FileUtil.class.getClassLoader().getResource("").getFile();
    }

    public static void writeString(String destPath, String code) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(destPath)) {
            printWriter.println(code);
        }
    }

    private static String handlerReplacement(String raw, List<ReplaceEntry> replaceEntries) {
        String resolved = raw;
        for (ReplaceEntry re : replaceEntries) {
            resolved = resolved.replace(re.getKey(), re.getReplaceValue());
        }
        return resolved;
    }
}
