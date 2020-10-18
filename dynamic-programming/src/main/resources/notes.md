动态规划问题求解过程：

- 定义整体问题
- 定义子问题
- 求解子问题
- 定义子问题之间的关系
- 递归 + 记忆: 或者是通过递归，或者是自底向上构建中间结果dp table。
需要注意的是：无循环依赖，即保证拓扑顺序
- 通过合并子问题来解决整体问题

5 easy steps to dynamic programming
- (a) define sub-problems              ==> count # sub-problem
- (b) guess (part of solution)        ==> count # choices
- (c) relate sub-problem solutions     ==> compute time/sub-problem
- (d) recurse + memoize       ==> time = time/sub-problem · # sub-problems
OR build DP table bottom-up
check sub-problems acyclic/topological order

- (e) solve the original problem: = a sub-problem OR by combining sub-problem solutions
count # sub-problem count # choices
compute time/sub-problem
time = time/sub-problem · # sub-=⇒ extra time
