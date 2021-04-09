package 每日一题;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <pre>
 * 某实验室计算机待处理任务以 [start,end,period] 格式记于二维数组 tasks，
 * 表示完成该任务的时间范围为起始时间 start 至结束时间 end 之间，需要计算机投入 period 的时长，
 *
 * 注意：
 * 1. period 可为不连续时间
 * 2. 首尾时间均包含在内
 * 处于开机状态的计算机可同时处理任意多个任务，请返回电脑最少开机多久，可处理完所有任务。
 *
 * </pre>
 *
 * @author Zhang Yi
 */
public class 批量处理任务 {
    public static void main(String[] args) {

    }

    public int processTasks(int[][] tasks) {
        //按起始时间排序 + 贪心选择 or 动态规划
        //盒子里面套盒子
        //由于"处于开机状态的计算机可同时处理任意多个任务"，
        //所以执行时间尽量重叠在一起，加上起始截止时间约束，得到最小执行时间

        Arrays.sort(tasks, Comparator.comparingInt(a -> a[0]));

        //todo
        return 0;
    }

}
