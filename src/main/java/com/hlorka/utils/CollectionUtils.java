package com.hlorka.utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by lbilenki on 1/23/2017.
 */
public class CollectionUtils {

    public static <T> Set<T> asSet(T... values) {
        return Arrays.stream(values).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static <T> List<T> asList(T... values) {
        return Arrays.stream(values).collect(Collectors.toList());
    }

    public static <K, V> Map<K, V> asMap(Function<V, K> keySuppler, V... values) {
        return asMap(keySuppler, Arrays.asList(values));
    }

    public static <K, V> Map<K, V> asMap(Function<V, K> keySuppler, Collection<V> values) {
        Map<K, V> map = new HashMap<>();
        values.stream().forEach(v -> map.put(keySuppler.apply(v), v));
        return map;
    }

    public static <K, V> Map<K, V> asMap(Object ... pairs) {
        if (pairs.length % 2 != 0) {
            throw new IllegalArgumentException(String.format("parm count not even: %d", pairs.length));
        }

        Map<K, V> map = new HashMap<>();
        for (int i = 0; i < pairs.length; i += 2) {
            map.put((K) pairs[i], (V) pairs[i+1]);
        }
        return map;
    }
}

