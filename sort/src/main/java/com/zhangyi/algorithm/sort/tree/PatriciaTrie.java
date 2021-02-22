package com.zhangyi.algorithm.sort.tree;

/**
 * 基数树<br/>
 * Patricia trie. Also known as: crit-bit tree, radix tree.<br>
 * 基数树支持插入、删除、查找操作。查找包括完全匹配、前缀匹配、前驱查找、后继查找。所有这些操作都是O(k)复杂度，其中k是所有字符串中最大的长度。<br>
 * https://en.wikipedia.org/wiki/Radix_tree
 *
 * <p>
 * In computer science, a radix tree (also radix trie or compact prefix tree) is a data structure that represents a
 * space-optimized trie (prefix tree) in which each node that is the only child is merged with its parent. The result is
 * that the number of children of every internal node is at most the radix r of the radix tree, where r is a positive
 * integer and a power x of 2, having x ≥ 1. Unlike regular trees, edges can be labeled with sequences of elements as
 * well as single elements. This makes radix trees much more efficient for small sets (especially if the strings are
 * long) and for sets of strings that share long prefixes.
 * <p>
 * Unlike regular trees (where whole keys are compared en masse from their beginning up to the point of inequality), the
 * key at each node is compared chunk-of-bits by chunk-of-bits, where the quantity of bits in that chunk at that node is
 * the radix r of the radix trie. When the r is 2, the radix trie is binary (i.e., compare that node's 1-bit portion of
 * the key), which minimizes sparseness at the expense of maximizing trie depth—i.e., maximizing up to conflation of
 * nondiverging bit-strings in the key. When r is an integer power of 2 having r ≥ 4, then the radix trie is an r-ary
 * trie, which lessens the depth of the radix trie at the expense of potential sparseness.
 * <p>
 * As an optimization, edge labels can be stored in constant size by using two pointers to a string (for the first and
 * last elements).[1]
 * <p>
 * Note that although the examples in this article show strings as sequences of characters, the type of the string
 * elements can be chosen arbitrarily; for example, as a bit or byte of the string representation when using multibyte
 * character encodings or Unicode.
 * <p>
 *
 *
 * <pre>
 * 参考：[Practical Algorithm to Retrieve Information Coded in Alphanumeric]
 * ・Remove one-way branching.
 * ・Each node represents a sequence of characters.
 * ・Implementation: one step beyond this course.
 *
 *
 * Applications.
 * ・IP routing tables: find longest prefix match.
 * ・Compressed quad-tree for N-body simulation.
 * ・Efficiently storing and querying XML documents
 * </pre>
 *
 * @author Zhang Yi
 */
public class PatriciaTrie {
}
