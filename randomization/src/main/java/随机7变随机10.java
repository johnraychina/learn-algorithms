/**
 * <pre>
 * https://leetcode-cn.com/problems/implement-rand10-using-rand7/solution/cong-zui-ji-chu-de-jiang-qi-ru-he-zuo-dao-jun-yun-/
 *
 * Part1: 随机数扩展：乘法
 * 已知 rand_N() 可以等概率的生成[1, N]范围的随机数
 * 那么：
 * (rand_X() - 1) × Y + rand_Y() ==> 可以等概率的生成[1, X * Y]范围的随机数
 * 即实现了 rand_XY()
 *
 * Part2: 随机数缩小：取模
 * 那么想到通过rand4()来实现rand2()呢？这个就很简单了，已知rand4()会均匀产生[1,4]的随机数，通过取余，再加1就可以了。如下所示，结果也是等概率的。
 *
 * Part3: 拒绝采样
 * ok，现在回到本题中。已知rand7()，要求通过rand7()来实现rand10()。
 * 有了前面的分析，要实现rand10()，就需要先实现rand_N()，并且保证N大于10且是10的倍数。
 * 这样再通过rand_N() % 10 + 1 就可以得到[1,10]范围的随机数了。
 * 而实现rand_N()，我们可以通过part 1中所讲的方法对rand7()进行改造，如下：
 * (rand7()-1) × 7 + rand7()  ==> rand49()
 * 但是这样实现的N不是10的倍数啊！这该怎么处理？这里就涉及到了“拒绝采样”的知识了，也就是说，如果某个采样结果不在要求的范围内，则丢弃它。
 * 基于上面的这些分析，再回头看下面的代码，想必是不难理解了。
 *
 * Part 4: 优化
 *
 *
 * 作者：kkbill
 * 链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7/solution/cong-zui-ji-chu-de-jiang-qi-ru-he-zuo-dao-jun-yun-/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * </pre>
 */

/**
 * <pre>
 * 已有方法rand7可生成 1 到 7 范围内的均匀随机整数，试写一个方法rand10生成 1 到 10 范围内的均匀随机整数。
 *
 * 不要使用系统的Math.random()方法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 随机7变随机10 {

    public static void main(String[] args) {

    }

    public static int rand10() {

        while (true) {
            int num = ((rand7() - 1) * 7 + rand7());
            if (num <= 40) { //如果大于40 (41~49) 则继续生成，1~40每个数的概率是相等的
                return num % 10 + 1;
            }
        }
    }

    public static int rand7() {
        return (int)(Math.random() * 8);
    }

    /**
     * 经过优化的版本（尽量少调用rand7)，复用前一次生成的随机数。
     *
     * @return
     */
    public int rand10Optimized() {
        while (true) {
            int a = rand7();
            int b = rand7();
            int num = (a - 1) * 7 + b; // rand 49
            if (num <= 40) {
                return num % 10 + 1; // 拒绝采样
            }

            a = num - 40; // rand 9
            b = rand7();
            num = (a - 1) * 7 + b; // rand 63
            if (num <= 60) { return num % 10 + 1; }

            a = num - 60; // rand 3
            b = rand7();
            num = (a - 1) * 7 + b; // rand 21
            if (num <= 20) { return num % 10 + 1; }
        }
    }
}
