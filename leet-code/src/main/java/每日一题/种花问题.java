package 每日一题;

/**
 * 种花问题 https://leetcode-cn.com/problems/can-place-flowers/
 * <pre>
 *
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 *
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 *
 * 示例 1:
 *
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 * 示例 2:
 *
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 * 注意:
 *
 * 数组内已种好的花不会违反种植规则。
 * 输入的数组长度范围为 [1, 20000]。
 * n 是非负整数，且不会超过输入数组的大小。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 种花问题 {
    public static void main(String[] args) {
        System.out.println(canPlaceFlowers(new int[] {1, 0, 0, 0, 0, 1}, 2));
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int m) {
        //只有连续空位才能种植，起步条件是3个连续0（除了最左侧之外），可种1朵花，后续每2个连续0可增加1朵花
        //归纳起来就是连续0区间 (start0 - end0)/2
        //各个0区间加起来即可
        //要计算空位，需要0区间位置，当前位置用i表示，0区间起始位置用s表示
        //为了简化边界处理， 我们虚拟最左侧位置flowerbed[-1]= 0，虚拟最右侧位置flowerbed[n]=1
        int N = flowerbed.length;
        int availableCount = 0;
        int s = -2;
        boolean inZeros = true; //true表示在0区间，false表示结束0区间
        for (int i = 0; i < flowerbed.length; i++) {
            //0区间开始:从非0到0
            if (!inZeros && flowerbed[i] == 0) {
                s = i; //重置0区间
                inZeros = true;
            }
            //之前是0
            else if (inZeros) {
                //0区间结束：从0到1或者i为结束
                if (flowerbed[i] == 1) {
                    availableCount += ((i - s - 1) / 2);
                    inZeros = false;
                } else if (flowerbed[i] == 0 && i == N - 1) {
                    //对结束位置特殊处理，虚拟位置flowerbed[n]=1
                    availableCount += ((N - s) / 2);
                }
            }
        }

        return m <= availableCount;
    }
}
