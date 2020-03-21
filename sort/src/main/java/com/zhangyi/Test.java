package com.zhangyi;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author 张义 johnraychina@163.com
 */
public class Test {

    public static void main(String[] args) {

        LocalDate bizDate = LocalDate.now();
        Date firstDayOfThisMonth = Date.from(bizDate.withDayOfMonth(1)
            .atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(firstDayOfThisMonth);

    }
}
