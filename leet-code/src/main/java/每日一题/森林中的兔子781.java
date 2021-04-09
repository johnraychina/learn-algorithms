package 每日一题;

import java.util.Arrays;

/**
 * <pre>
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。
 * 我们将这些回答放在 answers 数组里。
 *
 * 返回森林中兔子的最少数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rabbits-in-forest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 森林中的兔子781 {

    public static void main(String[] args) {
        //System.out.println(numRabbits(new int[]{1, 1, 2}));
        //System.out.println(numRabbits(new int[]{10, 10, 10}));
        System.out.println(numRabbits(new int[] {1, 1, 1, 1}));

    }

    public static int numRabbits(int[] answers) {
        //相同数字的可能是一伙，相当于合并同类项，会使得兔子数目最小化
        //我们只要统计有多少组不同的数字，相加即可。
        //可以将问题转化为排序 + 统计问题
        Arrays.sort(answers);

        int rabbitCnt = 0;
        int currentAnswer = -1; //用-1，可以让第一次循环命中if条件
        int prevIdx = -1; //上组兔子右边界
        for (int i = 0; i < answers.length; i++) {
            if (currentAnswer != answers[i]) {
                currentAnswer = answers[i];
                //answers: 还有多少其他的兔子和自己有相同的颜色，这里还要算上自己这只兔子
                rabbitCnt += (currentAnswer + 1);
            } else {
                //上面逻辑有个bug：比如有3只兔子都回答1，按上面逻辑rabbitCnt是2，但是显然rabbitCnt不可能比3还小
                //所以即使answer相同的一组兔子，也要要按 answer 做切分
                if ((currentAnswer + 1) < (i - prevIdx)) {
                    rabbitCnt += (currentAnswer + 1);
                    prevIdx = i;
                }
            }
        }

        return rabbitCnt;
    }
}
