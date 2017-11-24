package com.singulariti.os.ephemeris.utils;

import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 * @author John
 */
public class CollectionUtils {

    public static <T> Collector<T, ?, T> singletonCollector() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException("More than 1 item found");
                    }
                    return list.get(0);
                }
        );
    }
}
