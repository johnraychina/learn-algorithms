


# Self-balancing binary search tree 
https://en.wikipedia.org/wiki/Self-balancing_binary_search_tree
https://zh.wikipedia.org/wiki/%E5%B9%B3%E8%A1%A1%E6%A0%91
为什么要做自平衡：避免退化为链表，性能低下。
自平衡二叉查找树：在二叉查找树插入、删除时做自平衡，保持一个很小的树高。

In computer science, a self-balancing (or height-balanced) binary search tree is any node-based binary search tree that automatically keeps its height (maximal number of levels below the root) small in the face of arbitrary item insertions and deletions.[1]

These structures provide efficient implementations for mutable ordered lists, and can be used for other abstract data structures such as associative arrays, priority queues and sets.

The red–black tree, which is a type of self-balancing binary search tree, was called symmetric binary B-tree[2] and was renamed but can still be confused with the generic concept of self-balancing binary search tree because of the initials.

## implementations
2–3 tree
Red–black tree
AVL tree
B-tree

AA tree
Scapegoat tree
Splay tree
Treap
Weight-balanced tree


# Red-Black tree
https://en.wikipedia.org/wiki/Red-black_tree
https://zh.wikipedia.org/wiki/%E7%BA%A2%E9%BB%91%E6%A0%91
红黑树（英語：Red–black tree）是一种自平衡二叉查找树，是在计算机科学中用到的一种数据结构，典型的用途是实现关联数组。
它在1972年由鲁道夫·贝尔发明，被称为"对称二叉B树"，它现代的名字源于Leo J. Guibas和Robert Sedgewick于1978年写的一篇论文。
红黑树的结构复杂，但它的操作有着良好的最坏情况运行时间，
并且在实践中高效：它可以在O(logn)时间内完成查找、插入和删除，这里的 n 是树中元素的数目。

In computer science, a red–black tree is a kind of self-balancing binary search tree. Each node stores an extra bit representing color, used to ensure that the tree remains approximately balanced during insertions and deletions.[2]

When the tree is modified, the new tree is rearranged and repainted to restore the coloring properties that constrain how unbalanced the tree can become in the worst case. The properties are designed such that this rearranging and recoloring can be performed efficiently.

The re-balancing is not perfect, but guarantees searching in O(log n) time, where n is the number of nodes of the tree. The insertion and deletion operations, along with the tree rearrangement and recoloring, are also performed in O(log n) time.[3]

Tracking the color of each node requires only 1 bit of information per node because there are only two colors. The tree does not contain any other data specific to its being a red–black tree so its memory footprint is almost identical to a classic (uncolored) binary search tree. In many cases, the additional bit of information can be stored at no additional memory cost.


