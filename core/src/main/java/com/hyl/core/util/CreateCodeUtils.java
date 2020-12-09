package com.hyl.core.util;

import cn.hutool.core.util.StrUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static freemarker.template.Configuration.VERSION_2_3_30;

public class CreateCodeUtils {
    public static final String DRIVER = "mysql驱动 看是5还是8";
    public static final String URL = "jdbc:mysql连接地址";
    public static final String USERNAME = "账号";
    public static final String PASSWORD = "密码";

    public static void main(String[] args) {

        String tableName = "查询表名";
        String entityName = StrUtil.toCamelCase(tableName.substring(2));//驼峰命名
        String UpperObjectName = entityName.substring(0, 1).toUpperCase() + entityName.substring(1);// 首字母大写

        Map<String, Object> root = new HashMap<>();
        root.put("datebaseName", tableName);
        root.put("entity", UpperObjectName);
        root.put("entityName", entityName);
        root.put("data", DatabaseUtil.columns(tableName));

        Integer storeType = 1;//存储位置 1-按业务类 2-按功能类

        /* 生成serviceImpl */
        printFile("ControllerTemplate.ftl", root, UpperObjectName + "Controller.java", storeType);
        /* 生成serviceImpl */
        printFile("ServiceImplTemplate.ftl", root, UpperObjectName + "ServiceImpl.java", storeType);
        /* 生成serviceImpl */
        printFile("ServiceTemplate.ftl", root, UpperObjectName + "ServiceI.java", storeType);
        /* 生成serviceImpl */
        printFile("GatewayTemplate.ftl", root, UpperObjectName + "Gateway.java", storeType);
        /* 生成serviceImpl */
        printFile("RepositoryTemplate.ftl", root, UpperObjectName + "Repository.java", storeType);
        /* 生成serviceImpl */
        printFile("MapperTemplate.ftl", root, UpperObjectName + "Mapper.java", storeType);
        /* 生成serviceImpl */
        printFile("XmlTemplate.ftl", root, UpperObjectName + "Mapper.xml", storeType);
        /* 生成serviceImpl */
        printFile("DOTemplate.ftl", root, UpperObjectName + "DO.java", storeType);
        /* 生成serviceImpl */
        printFile("DOMTemplate.ftl", root, UpperObjectName + ".java", storeType);

        /* 本项目 */
        /* 生成controller */
//        printFile("ControllerTemplate.ftl", root, UpperObjectName + "Controller.java", "\\controller\\");
//        /* 生成query */
//        printFile("queryTemplate.ftl", root, UpperObjectName + "Query.java", "\\query\\");
//        /* 生成service */
//        printFile("ServiceTemplate.ftl", root, UpperObjectName + "Service.java", "\\service\\");
//        /* 生成serviceImpl */
//        printFile("serviceImplTemplate.ftl", root, UpperObjectName + "ServiceImpl.java", "\\service\\impl\\");
//        /* 生成mapper */
//        printFile("mapperTemplate.ftl", root, UpperObjectName + "Mapper.java", "\\dao\\");

        System.out.println("生成完毕！");
    }

    public static void printFile(String ftlName, Map<String, Object> root, String outFile, Integer storeType) {
        String filePath = null;
        if (storeType.equals(1)) {
            filePath = root.get("entity") + "\\";
        } else if (storeType.equals(2)) {
            filePath = "\\接口\\" + ftlName.split("Template")[0] + "\\";
        }
        printFile(ftlName, root, outFile, filePath);
    }

    public static void printFile(String ftlName, Map<String, Object> root, String outFile, String filePath) {
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
    public static Template getTemplate(String ftlName) {
        try {
            Configuration cfg = new Configuration(VERSION_2_3_30); // 通过Freemaker的Configuration读取相应的ftl
            cfg.setEncoding(Locale.CHINA, "utf-8");
            cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir")+ "\\core\\src\\main\\java\\com\\hyl\\core\\util\\template\\company\\")); // 设定去哪里读取相应的ftl模板文件
            Template temp = cfg.getTemplate(ftlName); // 在模板文件目录中找到名称为name的文件
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
