四种算法设计范式：
- 分而治之
- 随机化
- 贪心算法
- 动态规划

Design Paradigms:
- Divide & conquer (see Part I)
- Randomized algorithms (touched in Part I) 
- Greedy algorithms (next)
- Dynamic programming (later in Part II)


贪心策略注意点：

DANGER: Most greedy algorithms are NOT correct. (Even if your intuition says otherwise!)
Diakstra最短路径算法，用的是贪心策略。
如果有负数边，可能会导致贪心策略失效，导致找到的不是最短路径。
使用贪心策略时，要证明和推导贪心策略可行。


贪心策略适用场景：
- The Caching Problem
Optimal Cache Algorithm: 
"furthest in future" algorithm
严格实现是不可能的，因为我们不知道未来的所有访问序列。
但是这个思路仍然是有价值的：
我们可以假设局部性locality: 最近访问的数据也会被访问，很久没访问的数据以后也不太会被访问==>得到LRU算法























