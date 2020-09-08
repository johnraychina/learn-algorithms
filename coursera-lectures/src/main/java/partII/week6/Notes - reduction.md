# 全面思考问题

前面的章节都在关注解决单个问题
这个章节在进行系统化、方法论层面的思考：

**主题**
- Reduction: 设计算法，设定下界，将问题分类
- 线性规划：终极的问题解决模型
- 问题是否可解？

**变换思维**
- 从解决单个问题 到 问题解决的思维模型
- 从线性、平方级别 到多项式、指数级别
- 从实现细节  到 概念框架

**目标**
- 将我们之前学到的算法置于更大的上下文中
- 向你介绍重要的、本质的观念
- 激发你更加深入学习算法

## Reduction 归纳

Definition:
Problem X reduces to problem Y if you can use an algorithm that solves Y to help solve X.
Reduction归纳: 如果能用一种解决问题Y的算法来帮助解决X， 就称为问题X可以归纳为Y。

Cost of solving X = total cost of solving Y + cost of reduction

Design algorithm: Given algorithm for Y, can solve X.

Mentality: Since I know how to solve Y, can I use that algorithm to solve X ?

这个原则可以用来评估一个问题潜在解的复杂性，避免钻牛角尖。
