package com.zhangyi.algorithm.sort.tree;

/**
 * 核心思想：
 * 保持平衡
 * 一个2-3节点内，保持红色链接靠左left-leaning
 * 一个2-3节点内，较大值指向parent
 *
 * same code handles all cases(transform one case into another):
 * left-rotate
 * right-rotate
 * flip colors
 *
 *
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class RedBlackBST {

    //思考顺序:
    //2-3的删除操作非常复杂和困难，用 Hibbard deletion需要判断很多case
}
