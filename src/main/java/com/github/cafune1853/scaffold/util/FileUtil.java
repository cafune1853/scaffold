package com.github.cafune1853.scaffold.util;


import com.github.cafune1853.scaffold.generator.ReplaceEntry;

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
	
	public static void cpAll(final String srcDirPath, final String destDirPath) {
		File srcDir = new File(srcDirPath);
		File desDir = new File(destDirPath);
		if (!srcDir.exists()) {
			return;
		}
		srcDir.mkdirs();
		desDir.mkdirs();
		File[] allFile = srcDir.listFiles();
		for (File file : allFile) {
			String subFileName = file.getName();
			if (file.isDirectory()) {
				cpAll(srcDirPath + File.separator + subFileName, destDirPath + File.separator + subFileName);
			} else {
				cp(srcDirPath + File.separator + subFileName, destDirPath + File.separator + subFileName);
			}
		}
	}

	public static void replaceAllFiles(String dirs, List<ReplaceEntry> replaceEntryList){
		File dir = new File(dirs);
		if(dir.exists() && dir.isDirectory()){
			File[] allFile = dir.listFiles();
			for (File file : allFile) {
				if (file.isDirectory()) {
					replaceAllFiles(dirs + file.getName() + File.separator, replaceEntryList);
				} else {
					handlerReplacement(file, replaceEntryList);
				}
			}
		}
	}
	
	private static void handlerReplacement(File file, List<ReplaceEntry> replaceEntries) {
		StringBuilder stringBuilder = new StringBuilder();
		try (Scanner scanner = new Scanner(file)){
			while(scanner.hasNextLine()){
				String resolved = scanner.nextLine();
				for (ReplaceEntry re : replaceEntries) {
					resolved = resolved.replace(re.getKey(), re.getReplaceValue());
				}
				stringBuilder.append(resolved);
				stringBuilder.append("\n");
			}
		}catch (IOException ioe){
			System.out.println(ioe);
		}
		try (PrintWriter printWriter = new PrintWriter(file)){
			printWriter.print(stringBuilder.toString());
		}catch (IOException ioe){
			System.out.println(ioe);
		}
	}
}
