

## 1. Prefix free codes. 
In data compression, a set of binary strings is if no string is a prefix of another. 
For example, {01,10,0010,1111} is prefix free, but {01,10,0010,10100} is not because 10 is a prefix of 10100. 
Design an efficient algorithm to determine if a set of binary strings is prefix-free. 
The running time of your algorithm should be proportional the number of bits in all of the binary stings.

Hint: insert the binary strings into a 2-way trie.

Remark: it's also possible to solve this problem using radix sorting or a ternary search trie.

## 2. Boggle. 
Boggle is a word game played on an 44-by-44 grid of tiles, where each tile contains one letter in the alphabet. 
The goal is to find all words in the dictionary that can be made by following a path of adjacent tiles (with no tile repeated), 
where two tiles are adjacent if they are horizontal, vertical, or diagonal neighbors.

Hint: create a trie containing all of the words in the dictionary.

## 3. Suffix trees. 
Learn about and implement , the ultimate string searching data structure.

Warning: very difficult material ahead.

