package com.hyl.core.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyl.core.annotation.ApiVersion;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

/**
 * swagger转postman json文件
 */
public class Swagger2PostmanUtil {
    public static final String name = "课程购买";
    public static final String version = "API_3.2.0";
    public static final String token = "";

    public static void main(String[] args) {

        JSONObject jsonObject = new JSONObject();

        JSONObject info = new JSONObject();
        info.put("_postman_id", UUID.randomUUID().toString());
        info.put("name", name);
        info.put("schema", "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
        jsonObject.put("info", info);

        JSONObject protocolProfileBehavior = new JSONObject();
        jsonObject.put("protocolProfileBehavior", protocolProfileBehavior);

        jsonObject.put("item", scanPackge());

        jsonObject.put("auth", authFromParent());

        File file = FileUtil.newFile("C:\\postmanJson\\" + name + ".postman_collection.json");
        FileUtil.writeUtf8String(jsonObject.toJSONString(), file);
        System.out.println("创建完毕");
    }

    /**
     * 扫包 获取接口
     *
     * @return
     */
    private static JSONArray scanPackge() {
        JSONArray item = new JSONArray();
        Set<Class<?>> set = ClassUtil.scanPackage("com.startom.web.controller.OrderController");
        set.forEach(clazz -> {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                ApiVersion at = method.getAnnotation(ApiVersion.class);
                if (at != null) {
                    if (checkVersion(at)) {
                        System.out.println(clazz.getName());
                        System.out.println(method.getName());
                        String uriPath = uriPathOfClass(clazz);
                        item.add(item(method, uriPath));
                    }
                }
            }
        });
        return item;
    }

    /**
     * 检查版本
     * @param at
     * @return
     */
    private static boolean checkVersion(ApiVersion at) {
        for (String s : at.group()) {
            if (version.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 类的映射路径
     * @param clazz
     * @return
     */
    private static String uriPathOfClass(Class<?> clazz) {
        RequestMapping mapping = clazz.getAnnotation(RequestMapping.class);
        if (mapping != null) {
            return mapping.value()[0];
        }
        return "";
    }

    private static JSONObject item(Method method, String uriPath) {
        JSONObject object = new JSONObject();
        JSONArray response = new JSONArray();
        object.put("response", response);

        JSONObject request = new JSONObject();
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(ApiOperation.class)) {
                ApiOperation at = (ApiOperation) annotation;
                object.put("name", at.value());
            }
            if (annotation.annotationType().equals(PostMapping.class)) {
                request.put("method", "POST");
                PostMapping mapping = (PostMapping) annotation;
                request.put("url", postUrl(uriPath, mapping.value()[0]));
                request.put("body", postBody(method));
            } else if (annotation.annotationType().equals(GetMapping.class)) {
                request.put("method", "GET");
                GetMapping mapping = (GetMapping) annotation;
                request.put("url", getUrl(uriPath, mapping.value()[0], method));
                request.put("body", getBody());
            }

        }
        //传递token用
//        request.put("header", header());
//        request.put("auth", auth());
        object.put("request", request);
        return object;
    }

    private static JSONObject authFromParent() {
        return auth();
    }

    /**
     * header参数
     * @return
     */
    private static JSONArray header() {
        JSONArray header = new JSONArray();
        JSONObject auth = new JSONObject();
        auth.put("key", "Authorization");
        auth.put("value", token);
        auth.put("type", "text");
        header.add(auth);
        return header;
    }

    private static JSONObject auth() {
        JSONObject auth = new JSONObject();
        auth.put("type", "bearer");
        JSONArray bearer = new JSONArray();
        JSONObject bearerToken = new JSONObject();
        bearerToken.put("key", "token");
        bearerToken.put("value", token);
        bearerToken.put("type", "string");

        bearer.add(bearerToken);
        auth.put("bearer", bearer);
        return auth;
    }

    /**
     * 获取参数 附带注释
     * @param parameters
     * @return
     */
    private static String rawWithComment(Parameter[] parameters) {
        StringBuilder builder = null;
        JSONObject params = new JSONObject();
        for (Parameter parameter : parameters) {
            Field[] fields = ReflectUtil.getFieldsDirectly(parameter.getType(), true);
            for (Field field : fields) {
                if ("bizScenario".equals(field.getName()) || "serialVersionUID".equals(field.getName())) {
                    break;
                }
                params.put(field.getName(), "");
            }

            String str = params.toJSONString();
            String[] strArr = str.split(",");
            builder = new StringBuilder();
            for (int i = 0; i < strArr.length; i++) {
                if (i == strArr.length-1) {
                    strArr[i] = strArr[i].substring(0,strArr[i].length()-1);
                }
                builder.append(strArr[i]);
                if (i != strArr.length-1) {
                    builder.append(",");
                }
                ApiModelProperty property = fields[i].getAnnotation(ApiModelProperty.class);
                if (property != null) {
                    builder.append(" //").append(property.value()).append("\n\t");
                }
                if (i == strArr.length-1) {
                    builder.append("}");
                }
            }
        }
        return builder.toString();
    }

    /**
     * 获取参数
     * @param parameters
     * @return
     */
    private static String raw(Parameter[] parameters) {
        JSONObject params = new JSONObject();
        for (Parameter parameter : parameters) {
            Field[] fields = ReflectUtil.getFieldsDirectly(parameter.getType(), true);
            for (Field field : fields) {
                if ("bizScenario".equals(field.getName()) || "serialVersionUID".equals(field.getName())) {
                    break;
                }
                params.put(field.getName(), "");
            }
        }
        return params.toJSONString();
    }

    private static JSONObject postUrl(String uriPath, String suffix) {
        String path = uriPath + suffix;
        JSONObject url = new JSONObject();
        url.put("raw", "http://{{host}}/" + path);
        url.put("protocol", "http");
        url.put("host", "{{host}}");
        url.put("path", pathArray(path));
        return url;
    }

    private static JSONObject postBody(Method method) {
        JSONObject body = new JSONObject();
        body.put("mode", "raw");
        body.put("raw", raw(method.getParameters()));

        JSONObject options = new JSONObject();
        JSONObject raw = new JSONObject();
        raw.put("language", "json");
        options.put("raw", raw);
        body.put("options", options);

        return body;
    }

    private static JSONObject getUrl(String uriPath, String suffix, Method method) {
        String path = uriPath + suffix;
        JSONObject url = new JSONObject();
        url.put("raw", "http://{{host}}/" + path);
        url.put("protocol", "http");
        url.put("host", "{{host}}");
        url.put("path", pathArray(path));
        url.put("query", query(method.getParameters()));
        return url;
    }

    private static JSONObject getBody() {
        JSONObject body = new JSONObject();
        body.put("mode", "urlencoded");
        body.put("urlencoded", new JSONArray());

        JSONObject options = new JSONObject();
        JSONObject raw = new JSONObject();
        raw.put("language", "json");
        options.put("raw", raw);
        body.put("options", options);

        return body;
    }

    /**
     * 获取参数
     * @param parameters
     * @return
     */
    private static JSONArray query(Parameter[] parameters) {
        JSONArray query = new JSONArray();
        for (Parameter parameter : parameters) {
            RequestParam at = parameter.getAnnotation(RequestParam.class);
            if (at != null) {
                JSONObject params = new JSONObject();
                params.put("key", StrUtil.isNotBlank(at.name()) ? at.name() : at.value());
                params.put("value", "");
                query.add(params);
            }
        }
        return query;
    }

    private static JSONArray pathArray(String path) {
        String[] array = path.split("/");
        JSONArray jsonArray = new JSONArray();
        for (String s : array) {
            jsonArray.add(s);
        }
        return jsonArray;
    }
}
