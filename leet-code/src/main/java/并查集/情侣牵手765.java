package 并查集;

/**
 * <pre>
 * https://leetcode-cn.com/problems/couples-holding-hands/
 *
 * 765. 情侣牵手
 * N 对情侣坐在连续排列的 2N个座位上，想要牵到对方的手。
 *
 * 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 * The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.
 *
 * 示例 1:
 *
 * 输入: row = [0, 2, 1, 3]
 * 输出: 1
 * 解释: 我们只需要交换row[1]和row[2]的位置即可。
 * 示例 2:
 *
 * 输入: row = [3, 2, 0, 1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 * 说明:
 *
 * len(row) 是偶数且数值在 [4, 60]范围内。
 * 可以保证row 是序列 0...len(row)-1 的一个全排列。
 * </pre>
 *
 * <pre>
 *     https://leetcode-cn.com/problems/couples-holding-hands/solution/qing-lu-qian-shou-by-leetcode-gl1c/
 *
 * </pre>
 *
 * @author Zhang Yi
 */
public class 情侣牵手765 {
    public class Solution {

        public int minSwapsCouples(int[] row) {
            int len = row.length;
            int N = len / 2;
            UnionFind unionFind = new UnionFind(N);
            for (int i = 0; i < len; i += 2) {
                unionFind.union(row[i] / 2, row[i + 1] / 2);
            }
            return N - unionFind.getCount();
        }

        private class UnionFind {

            private int[] parent;

            private int count;

            public UnionFind(int n) {
                this.count = n;
                this.parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int getCount() {
                return count;
            }

            public int find(int x) {
                while (x != parent[x]) {
                    parent[x] = parent[parent[x]];
                    x = parent[x];
                }
                return x;
            }

            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX == rootY) {
                    return;
                }

                parent[rootX] = rootY;
                count--;
            }
        }
    }
}
