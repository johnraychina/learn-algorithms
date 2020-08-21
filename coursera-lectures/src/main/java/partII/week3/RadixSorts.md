
# 基数排序 Radix Sort

问题：merge sort, quick sort, heap sort 已经达到最优解了， 还有更好的算法吗？

之前的算法都是用compareTo比较排序，有没有比他更加高效的办法呢？

# 键值索引计数 Key-Indexed Counting

假定：要排序对象的键值是从0到R-1的整数
可得到推论：我们可以用这个键值作为数组索引（位置）


可以达到线性级别 11N(排序对象数) + 4R(基数)


# Manber-Myers algorithm
相比 MSD Radix Sort, Manber-Myers algorithm 能保证最坏的情况下NlogN的性能，但是需要更多辅助内存。
