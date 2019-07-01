package com.hyl.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaUtil {
    public static <T, K, U> Map<K,U> list2Map(List<T>list, Function key) {
        return (Map<K, U>) list.stream().collect(Collectors.toMap(key, Function.identity(), (key1, key2) -> key2));
    }

    public static <T> String joinByComma(List<T>list, Function key) {
        return joinByDelimiter(list, key, ",");
    }

    public static <T> String joinByCommaWithNotBlank(List<T>list, Function key) {
        return joinByDelimiterWithNotBlank(list, key, ",");
    }

    public static <T> String joinByDelimiter(List<T>list, Function key, String delimiter) {
        return list.stream().map(key).collect(Collectors.joining(delimiter)).toString();
    }

    public static <T> String joinByDelimiterWithNotBlank(List<T>list, Function key, String delimiter) {
        Predicate<String> predicate = s -> StringUtils.isNotBlank(s);
        return list.stream().map(key).filter(predicate).collect(Collectors.joining(delimiter)).toString();
    }
}
