package com.hyl.core.util;

import cn.hutool.core.io.FileUtil;

import java.io.File;

public class FileRead {
    public static void main(String[] args) {
        File[] ls = FileUtil.ls("E:\\Git\\ssm");
        for (File l : ls) {
            System.out.println(l.getName()+ ": "+ l.getAbsolutePath());
        }
    }


}
