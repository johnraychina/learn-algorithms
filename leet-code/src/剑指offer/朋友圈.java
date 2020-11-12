package 剑指offer;

import java.util.Arrays;

/**
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。 他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * <p>
 * 给定一个N * N的矩阵M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/friend-circles 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Zhang Yi
 */
public class 朋友圈 {

    private int[] parent;

    private int find(int x) {
        if (parent[x] < 0) {
            return x;
        }
        // todo 这个递归是必须的，否则可能形成环，或者少连接
        return parent[x] = find(parent[x]);
    }

    private void union(int x, int y) {
        int xset = find(x);
        int yset = find(y);
        if (xset != yset) {
            parent[xset] = yset;
        }
    }

    public int findCircleNum(int[][] M) {
        parent = new int[M.length];
        Arrays.fill(parent, -1);
        for (int i = 0; i < M.length; i++) {
            // 因为连接的特殊性，A与B存在关系则B与A必定存在关系，故可以按照数组对角线裁剪掉一半的路径
            for (int j = i + 1; j < M.length; j++) {
                if (M[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        // 统计数组中最终还为-1的个数，所以的-1均代表一个union的顶点，也就是一个朋友圈

        // 使用stream流求和，语法简洁 但对于基础类型数据来讲，stream操作反而更慢
        // return (int) Arrays.stream(parent).filter(x -> x == -1).count();
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1) { count++; }
        }
        return count;
    }

}
