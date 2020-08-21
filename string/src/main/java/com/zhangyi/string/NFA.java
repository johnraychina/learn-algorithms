package com.zhangyi.string;

import algs4.Digraph;

/**
 * Non-deterministic  Finite Automation.
 *
 * find one of specified set of strings in text
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class NFA {

    private Digraph digraph; //directed graph of epsilon transitions
    private String regex; // regular expression
    private int M; // number of characters in regular expression

    //有限自动机要穷举所有的状态转换，需要指数级别的代价
    //
}
