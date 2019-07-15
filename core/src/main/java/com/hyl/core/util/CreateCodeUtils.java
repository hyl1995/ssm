package com.hyl.core.util;

import cn.hutool.core.util.StrUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static freemarker.template.Configuration.VERSION_2_3_28;

public class CreateCodeUtils {
    public static void main(String[] args) throws Exception {

        String tableName = "user";
        String entityName = StrUtil.toCamelCase(tableName);//驼峰命名
        String UpperObjectName = entityName.substring(0, 1).toUpperCase() + entityName.substring(1);// 首字母大写

        Map<String, Object> root = new HashMap<>();
        root.put("datebaseName", tableName);
        root.put("entity", UpperObjectName);
        root.put("entityName", entityName);

        /* 生成query */
//        printFile("queryTemplate.ftl", root, UpperObjectName + "Query.java", UpperObjectName + "\\query\\");
//        /* 生成sql脚本 */
//        printFile("sqlTemplate.ftl", root, tableName + ".xml", UpperObjectName + "\\mapper\\");
//        /* 生成service */
//        printFile("serviceTemplate.ftl", root, UpperObjectName + "Service.java", UpperObjectName + "\\" + entityName + "\\");
//        /* 生成serviceImpl */
//        printFile("serviceImplTemplate.ftl", root, UpperObjectName + "ServiceImpl.java", UpperObjectName + "\\service\\");

        /* 本项目 */
        /* 生成query */
        printFile("queryTemplate.ftl", root, UpperObjectName + "Query.java", "\\query\\");
        /* 生成service */
        printFile("serviceTemplate.ftl", root, UpperObjectName + "Service.java", "\\service\\");
        /* 生成serviceImpl */
        printFile("serviceImplTemplate.ftl", root, UpperObjectName + "ServiceImpl.java", "\\service\\impl\\");
        /* 生成mapper */
        printFile("mapperTemplate.ftl", root, UpperObjectName + "Mapper.java", "\\dao\\");

        System.out.println("生成完毕！");
    }

    public static void printFile(String ftlName, Map<String, Object> root, String outFile, String filePath) throws Exception {
        try {
            File file = new File("C:\\createCode\\" + filePath + outFile);
            if (!file.getParentFile().exists()) { // 判断有没有父路径，就是判断文件整个路径是否存在
                file.getParentFile().mkdirs(); // 不存在就全部创建
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            Template template = getTemplate(ftlName);
            template.process(root, out); // 模版输出
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过文件名加载模版
     *
     * @param ftlName
     */
    public static Template getTemplate(String ftlName) throws Exception {
        try {
            Configuration cfg = new Configuration(VERSION_2_3_28); // 通过Freemaker的Configuration读取相应的ftl
            cfg.setEncoding(Locale.CHINA, "utf-8");
            cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir")+ "\\core\\src\\main\\java\\com\\hyl\\core\\util\\template\\")); // 设定去哪里读取相应的ftl模板文件
            Template temp = cfg.getTemplate(ftlName); // 在模板文件目录中找到名称为name的文件
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
