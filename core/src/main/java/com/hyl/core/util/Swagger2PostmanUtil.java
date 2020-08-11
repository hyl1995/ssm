package com.hyl.core.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyl.core.annotation.ApiVersion;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public static void main(String[] args) {
        String name = "话题";
        String version = "API_3.2.0";
        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjVCQzcxQzkwQTUwOTVCNjA4OEU4MDQ1QTk3RDE1REIyQUJCOEQ4QzUiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJXOGNja0tVSlcyQ0k2QVJhbDlGZHNxdTQyTVUifQ.eyJuYmYiOjE1OTcxMDkzNzQsImV4cCI6MTU5NzE2Njk3NCwiaXNzIjoiaHR0cHM6Ly90c2FkbWluLnF1YW56aGFvLmNvIiwiYXVkIjpbImh0dHBzOi8vdHNhZG1pbi5xdWFuemhhby5jby9yZXNvdXJjZXMiLCJBY2NvdW50QXBpIiwiTWVtYmVyQXBpIiwiTVlXQXBpIiwiUGF5QXBpIiwic21zYXBpIl0sImNsaWVudF9pZCI6Ik1lbWJlckZyb250IiwiUHJvamVjdEtleSI6IjI5YTAzZGEzMmY0ODQ0YTliYTBlNjdhNGUwMDg2MmY2IiwiVXNlclNpZ25JZCI6IjEzIiwic3ViIjoiOTUwIiwiYXV0aF90aW1lIjoxNTk3MTA5Mzc0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbImN1c3RvbS5wcm9maWxlIiwiZW1haWwiLCJvcGVuaWQiLCJwcm9maWxlIiwicm9sZXMiLCJBY2NvdW50QXBpIiwiTWVtYmVyQXBpIiwiTVlXQXBpIiwiUGF5QXBpIiwic21zYXBpLnNlbmQiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.ICdWlbGTYvnYKD8YiTHhaLjAl8z_IdaAw5YDvcrby2wY8UCd1wy4vv6n_mRhoQIvZI14kcjmPRYjXT6e1IZkfjqZYrmWSmp55kyH_iFhmF8MR9117trl50QR5vpMzoJlMh5gamPFSLZaNlW2Q-LO7Wf7HrmX7Tyla2jLh1kMU9pJFDvuLZdSR-f6llZbQdA-Ra2qy-PR738-eSeA1jzKQmGCmpMVRfYirsxffu2cI5U1Q4kHz9Sp8vNJflKtmYMlBI9lo9D5TdIfAFP1TGuCPggT_KZ_KuzDajuXGh3F8xgX50JccvCUWqaS3BOvucL37QtxS8Avr9Zr9YF3ebxteA";
        JSONObject jsonObject = new JSONObject();

        JSONObject info = new JSONObject();
        info.put("_postman_id", UUID.randomUUID().toString());
        info.put("name", name);
        info.put("schema", "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
        jsonObject.put("info", info);

        JSONObject protocolProfileBehavior = new JSONObject();
        jsonObject.put("protocolProfileBehavior", protocolProfileBehavior);

        jsonObject.put("item", scanPackge(version, token));

        jsonObject.put("auth", authFromParent(token));

        File file = FileUtil.newFile("C:\\postmanJson\\" + name + ".postman_collection.json");
        FileUtil.writeUtf8String(jsonObject.toJSONString(), file);
        System.out.println("创建完毕");
    }

    /**
     * 扫包 获取接口
     *
     * @param token
     * @param version
     * @return
     */
    private static JSONArray scanPackge(String version, String token) {
        JSONArray item = new JSONArray();
        Set<Class<?>> set = ClassUtil.scanPackage("com.alib.controller");
        set.forEach(clazz -> {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {

                ApiVersion at = method.getAnnotation(ApiVersion.class);
                if (at != null) {
                    if (checkVersion(at, version)) {
                        System.out.println(clazz.getName() + " " + method.getName());
                        String uriPath = uriPathOfClass(clazz);
                        item.add(item(method, uriPath, token));
                    }
                }
            }
        });
        return item;
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

    /**
     * 检查版本
     * @param at
     * @param version
     * @return
     */
    private static boolean checkVersion(ApiVersion at, String version) {
        for (String s : at.group()) {
            if (version.equals(s)) {
                return true;
            }
        }
        return false;
    }

    private static JSONObject item(Method method, String uriPath, String token) {
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
                request.put("url", url(uriPath, mapping.value()[0]));
                request.put("body", body(method));
            } else if (annotation.annotationType().equals(GetMapping.class)) {
                request.put("method", "GET");
                PostMapping mapping = (PostMapping) annotation;
                request.put("url", url(uriPath, mapping.value()[0]));
            }

        }
        //传递token用
//        request.put("header", header(token));
//        request.put("auth", auth(token));
        object.put("request", request);
        return object;
    }

    private static JSONObject authFromParent(String token) {
        return auth(token);
    }

    /**
     * header参数
     * @return
     */
    private static JSONArray header(String token) {
        JSONArray header = new JSONArray();
        JSONObject auth = new JSONObject();
        auth.put("key", "Authorization");
        auth.put("value", token);
        auth.put("type", "text");
        header.add(auth);
        return header;
    }

    private static JSONObject auth(String token) {
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

    private static JSONObject body(Method method) {
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

    private static JSONObject url(String uriPath, String suffix) {
        String path = uriPath + suffix;
        JSONObject url = new JSONObject();
        url.put("raw", "http://{{host}}/" + path);
        url.put("protocol", "http");
        url.put("host", "{{host}}");
        url.put("path", pathArray(path));
        return url;
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
