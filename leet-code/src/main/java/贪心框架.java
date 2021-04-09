/**
 * <pre>
 * 贪心算法可以看作动态规划算法的特例，它需要满足贪心选择性质：局部最优解能导出全局最优解。
 * 因此效率也更高（更加激进的剪枝，每次选局部最优解），
 * 通常能将时间复杂度降低到线性级别。
 *
 * 总体来看效率：贪心算法 > 动态规划 > 回溯（暴力穷举）
 * 如果说回溯（暴力穷举）需要指数级时间，
 * 那么动态规划利用记忆表，消除重叠子问题，可以达到多项式级别时间，
 * 而如果问题满足贪心选择性质，则可以进一步将时间复杂度降低到线性级别。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 贪心框架 {

    public void choose() {

    }
}
