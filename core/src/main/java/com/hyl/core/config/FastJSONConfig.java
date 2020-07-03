package com.hyl.core.config;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @description: com.yhw.api.config
 * @author: hyl
 * @data: 2019/12/12/012
 */
@Configuration
public class FastJSONConfig {

    @PostConstruct
    public void init() {
        SerializeConfig config = SerializeConfig.globalInstance;
        config.put(LocalDateTime.class, new LocalDateTimeSerializer());
    }

    public static class LocalDateTimeSerializer implements ObjectSerializer {
        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features){
            SerializeWriter out = serializer.out;
            if (object == null) {
                out.writeNull();
            } else {
                LocalDateTime result = (LocalDateTime) object;
                out.writeLong(result.toInstant(ZoneOffset.of("+8")).toEpochMilli());
            }
        }
    }
}
