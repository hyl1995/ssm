package com.hyl.core.util;

import java.lang.reflect.Field;

/**
 * @description: com.hyl.core.util
 * @author: hyl
 * @data: 2019/9/25/025
 */
public class EncodingUtil {
    /**
     * 判定编码格式
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode;

        encode = "UTF-16";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "ASCII";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return "字符串<< " + str + " >>中仅由数字和英文字母组成，无法识别其编码格式";
            }
        } catch (Exception ex) {
        }

        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        /*
         *......待完善
         */

        return "未识别编码格式";
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
//        //获取系统默认编码
//        System.out.println("系统默认编码：" + System.getProperty("file.encoding")); //查询结果GBK
//        //系统默认字符编码
//        System.out.println("系统默认字符编码：" + Charset.defaultCharset()); //查询结果GBK
//        //操作系统用户使用的语言
//        System.out.println("系统默认语言：" + System.getProperty("user.language")); //查询结果zh
//
//        System.out.println();
//
//        String s1 = "hi, nice to meet you!";
//        String s2 = "hi, 我来了！";
//
//        System.out.println(getEncoding(s1));
//        System.out.println(getEncoding(s2));
        String a = new String("AAA");
        Class clazz = a.getClass();
        Field field = clazz.getDeclaredField("value");
        field.setAccessible(true);
        char[] ch = (char[]) field.get(a);
        ch[0] = 'B';
        System.out.println(a);
        System.out.println("AAA");
    }
}
