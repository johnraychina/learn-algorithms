package 贪心;

public class 加油站134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int start = 0;
        int n = gas.length;
        int sum = 0, minSum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            // 经过第i个站点后，使油量达到新低，所以第 i+1 号站点就是最低点（起点）
            if (sum < minSum) {
                minSum = sum;
                start = i + 1;
            }
        }
        // 总油量小于总消耗，无解
        if (sum < 0) {
            return -1;
        }
        return start == n ? 0 : start;
    }


}
