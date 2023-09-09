# Interview Questions: Radix Sorts (ungraded)

## 1.2-sum. 两数和
Given an array aa of nn 64-bit integers and a target value T, 
determine whether there are two distinct integers i and j such that a_i + a_j = Ta . 
Your algorithm should run in linear time in the worst case.

Hint: sort the array in linear time.
方案1、对将数组使用RadixSort基数排序
然后两个指针start, end一前一后移动按sum(start, end) 对比 target做移动即可

方案2、利用辅助数据结构HashMap
for e in array
  if (map.contains(T - e)) return pair<map.get(T-e), e>
  else put(e)

# 2.American flag sort. 美国旗
Given an array of n objects with integer keys between 0 and R-1, 
design a linear-time algorithm to rearrange them in ascending order. 
Use extra space at most proportional to R.

Hint: first compute the frequency counts for each integer, 
which tells you where the keys need to go. 
Then cyclically permute the keys into their proper places.

## 3.Cyclic rotations. 反转词
Two strings s and t are cyclic rotations of one another if they have the same length 
and s consists of a suffix of t followed by a prefix of t. 
For example, "suffixsort" and "sortsuffix" are cyclic rotations.

Given n distinct strings, each of length L, design an algorithm to determine whether 
there exists a pair of distinct strings that are cyclic rotations of one another. 

For example, the following list of n = 12 strings of length L = 10
 contains exactly one pair of strings ("suffixsort" and "sortsuffix") that are cyclic rotations of one another.

The order of growth of the running time should be nL^2 (or better) in the worst case. 
Assume that the alphabet size R is a small constant.

Signing bonus. Do it in NnL time in the worst case.

Hint: define a fingerprint of a string in such a way that two strings are cyclic rotations of one another 
if and only if they have the same fingerprint.

Signing bonus: design an algorithm to find the fingerprint of a string of length L in time proportional to L in the worst case.
