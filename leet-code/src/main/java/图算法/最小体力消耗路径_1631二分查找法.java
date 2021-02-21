package 图算法;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/path-with-minimum-effort/solution/zui-xiao-ti-li-xiao-hao-lu-jing-by-leetc-3q2j/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 最小体力消耗路径_1631二分查找法 {
    class Solution {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        /*
        复杂度分析：
        时间复杂度：O(m n log(H)): 对高度H差二分查找，log(H), 对每个格子遍历(seen数组) m*n
        空间复杂度: O(m n)，seen数组O(mn)，队列queue O(mn*2)
        */
        public int minimumEffortPath(int[][] heights) {
            int ans = Integer.MAX_VALUE;
            int m = heights.length;
            int n = heights[0].length;

            //按高度差做二分查找
            int low = 0, hi = 999999;
            while (low <= hi) {
                int mid = (low + hi) / 2;
                //将格子的二维坐标转换成一维坐标: v = i*n + j
                //按广度优先遍历上下左右（最小生成树）
                //seen数组标记已经遍历过的格子
                boolean[] seen = new boolean[m * n - 1];
                seen[0] = true;
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[] {0, 0}); // 初始顶点

                while (!queue.isEmpty()) {
                    //取一个顶点遍历上下左右 v -> u
                    int[] v = queue.poll();
                    int vi = v[0];
                    int vj = v[1];
                    for (int direction = 0; direction < 4; direction++) {
                        int ui = vi + dirs[direction][0]; //行坐标
                        int uj = vj + dirs[direction][1]; //列坐标
                        if (ui >= 0 && ui < m && uj >= 0 && uj < n
                            && !seen[ui * m + uj] && Math.abs(heights[ui][uj] - heights[vi][vj]) <= mid) {
                            queue.offer(new int[] {ui, uj});
                            seen[ui * m + uj] = true;
                        }
                    }
                }

                //遍历完成，判断使用左半边还是右半边
                //如果按mid这个高度上限可达到右下角，那么就用左半边继续缩小，否则使用右半边
                if (seen[m * n - 1]) {
                    ans = mid;
                    hi = mid;
                } else {
                    low = mid + 1;
                }
            }

            return ans;
        }
    }

}
