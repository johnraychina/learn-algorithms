# Graph

## Connected Components 
无向图的联通组件打标，算法比较简单，DFS+count即可实现。

## BFS(breath first search)
常见应用

## DFS(depth first search)
用stack可以替代递归recursive call的方式。
常见应用

# Digraph 有向图
Digraph是Graph的一个特例。
Graph 与 Digraph 数据结构和算法逻辑高度一致，把Graph的每条边Edge看做2条有方向的边，就变成了Digraph

## DAG(Directed Acyclic Graph) 有向无环图
常见应用

## DAG Topological Sort
### precedence scheduling 优先级调度
目标：给定一组任务，任务之间有先后约束关系，如何排定顺序？

### DAG Topological Sort 拓扑排序
定义：重新绘制DAG，节点排成一条线，边全部指向一个方向。
算法：
- 执行DFS: run depth first search 
- 生成反向后序遍历序列（叶子节点作为终点，先叶子后父节点生成一个序列）
return vertices in reverse postorder

### 有向图环形检测
应用: 优先级调度、继承检查、excel公式环形引用

## DAG Strong Connected Components 
应用：软件模块依赖分析、食物链分析
有向无环图 强联通组件打标， 证明过程非常需要技巧，但是算法非常简单，仅仅在 Connected Components基础上，对节点遍历顺序改为
Kosaraju-Sharir algorithm：
Phase 1: run DFS on G-Reverse to compute reverse postorder.  
把有向图G方向反转，生成反向后续遍历序列

2、run DFS in G,, considering vertices in order given by first DFS.
按上面的序列 执行DFS 遍历有向图G


 
# Challenge #1 如何检测一个Digraph有没有环呢？

