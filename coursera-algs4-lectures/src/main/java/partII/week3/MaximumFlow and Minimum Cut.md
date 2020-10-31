
## Mincut Problem  最小分割问题

s-t分割
* A st-cut is a partition of vertices into two disjoint sets 
which s is in one set A, and t is in the other set B.


容量
* Its capacity
is the sum of the capacity of edges from A to B.

最小s-t分割
* Minimum st-cut(Mincut) problem: find a cut of minimum capacity.

Applications 应用场景

- Cut supplies( if cold war become real war).
战争当中如何切断补给线

- Cut off network
切断网络


## Maxflow Problem 最大流量问题

* st-flow is an assignment of values to the edges such that:
 Capacity constraint: 0 <= edge's flow <= edge's capacity
 Local equilibrium: inflow == outflow at each vertices(except s and t)
 
* The value of a flow is the inflow at t.

* Maximum st-flow(Maxflow) problem: find a flow of maximum value.


Applications 应用场景
* maximize the supplies

* maximize flow of information to a specified set of people


