
# 基数排序 Radix Sort

问题：merge sort, quick sort, heap sort 已经达到最优解了， 还有更好的算法吗？

之前的算法都是用compareTo比较排序，有没有比他更加高效的办法呢？

# 键值索引计数 Key-Indexed Counting

假定：要排序对象的键值是从0到R-1的整数
可得到推论：我们可以用这个键值作为数组索引（位置）


可以达到线性级别 11N(排序对象数) + 4R(基数)

原理是：按有限的基数排序，每次对一位进行排序，按位数排多次即可。

什么时候适合使用基数排序？   
要排序的对象可以拆分为多位，每位的基数相对较小，比如：char类型，或者[0-9]等

##  MSD(Most-Significant-Digit first): 
- 实现：left-to-right 按位排序 + 下一位递归
- 弱点：需要做递归，实现相对复杂一些。
- 优点：符合直觉，容易理解，支持长度不想等的元素。

## LSD(Least-Significant-Digit first):
- 实现：right-to-left 按位排序
- 从右到左按位排序，实现简单，但是看起来违反直觉， 怎么确保最终有序呢？ 排序稳定性保证了。
- 弱点：因为要依赖每个次排序稳定性，不能对变长元素做填充，所有元素必须是相同长度才行。

# Manber-Myers algorithm
相比 MSD Radix Sort, Manber-Myers algorithm 能保证最坏的情况下NlogN的性能，但是需要更多辅助内存。
