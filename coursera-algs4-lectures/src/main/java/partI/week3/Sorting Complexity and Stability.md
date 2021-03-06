## 排序复杂度分析

前提：对于键值<i>不重复</i>的N个元素排序
把对比过程构造decision tree:
```latex
1、最坏情况要对比h次，高度为h的树，叶子节点 <= 2^h
2、由于N有N!种组合，所以叶子节点至少 >= N!

综合1，2有： 2^h >= 叶子节点 >= N!

理解叶子节点：问题空间[N!, 2^h] 

然后两边取lg有：
h>=lg(N!)    -->  h ~ Nlg(N)
```

## 排序稳定性分析
稳定排序：移动位置时，不会跨过相等的元素。

不稳定排序：移动位置时，可能跨过相等的元素。

