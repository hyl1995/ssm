package com.hyl.core.util;

import cn.hutool.core.io.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileRead {
    static Set<String> packgeSet = new HashSet<>();

    public static void main(String[] args) {
        File[] ls = getFiles("E:\\Git\\ssm");
//        for (File l : ls) {
//            System.out.println(l.getName()+ ": "+ l.getAbsolutePath());
//        }

        loopFiles(ls);

        for (String s : packgeSet) {
            System.out.println(s);
        }
    }

    private static void loopFiles(File[] ls) {
        for (File l : ls) {
            if (l.isFile()) {
                readJava(l);
            }
            if (l.isDirectory()) {//目录
                File[] files = getFiles(l.getPath());
                loopFiles(files);
            }
        }
    }

    private static File[] getFiles(String path) {
        File[] ls = FileUtil.ls(path);
        return ls;
    }

    private static String readJava(File file) {
        boolean isJava = file.getName().endsWith(".java");
        if (isJava) {//java文件
            BufferedReader reader = FileUtil.getReader(file, "utf-8");
            try {
                String packgeName = reader.readLine();
                packgeSet.add(packgeName);
                return packgeName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}