package com.zhangyi.algorithm.sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.zhangyi.algorithm.sort.priority_queue.Pair;

/**
 * @author johnraychina@163.com
 */
public class DatasetUtils {
    public static List<Pair<String, BigDecimal>> loadTransactions(List<String> lines) {

        return lines.stream()
            .map(line -> {
                String[] columns = line.split(",");
                return new Pair<>(columns[0], new BigDecimal(columns[1]));
            }).collect(Collectors.toList());
    }
}
