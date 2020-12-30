动态规划问题求解过程：

识别整体问题、变量、约束

- 定义子问题：有多少个子问题
- 猜想子问题的选择：一个子问题有多少个选择
- 子问题解法：每个子问题的计算时间
- 递归 + 记忆: 构建【状态转移方程】：通过递归或者自底向上构建中间结果dp table。
需要注意的是：需要保证子问题之间无循环依赖，即保证拓扑顺序
- 解决整体问题（通过合并子问题）

5 easy steps to dynamic programming
- (a) define sub-problems              ==> count # sub-problem
- (b) guess the choice (part of solution)  ==> count # choices
- (c) relate sub-problem solutions     ==> compute time/sub-problem
- (d) recurse + memoize       
==> time = time/sub-problem · # sub-problems
OR build DP table bottom-up
check sub-problems acyclic/topological order

- (e) solve the original problem: = a sub-problem OR by combining sub-problem solutions
count # sub-problem count # choices
compute time/sub-problem
time = time/sub-problem · # sub-=⇒ extra time
