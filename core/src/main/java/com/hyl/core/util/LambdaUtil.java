package com.hyl.core.util;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaUtil {
    public static <T, K, U> Map<K,U> list2Map(List<T>list, Function key) {
        return (Map<K, U>) list.stream().collect(Collectors.toMap(key, Function.identity(), (key1, key2) -> key2));
    }

    public static <T> String joinByComma(List<T>list, Function key) {
        return joinByDelimiter(list, key, ",");
    }

    public static <T> String joinByDelimiter(List<T>list, Function key, String delimiter) {
        return String.valueOf(list.stream().map(key).collect(Collectors.joining(delimiter)));
    }

}
