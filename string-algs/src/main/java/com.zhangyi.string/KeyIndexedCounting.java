package com.zhangyi.string;

/**
 * <pre>
 * 对象key值作为数组索引
 *
 * array[]数组 i - key值
 * count[]数组 key -accumulated key count
 * aux[]辅助数组作为临时有序数组
 *
 *
 * 线性时间
 * 需要辅助内存
 *
 * </pre>
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class KeyIndexedCounting {

    /**
     * @param array
     * @param R     基数，例如char是256
     */
    public void sort(int array[], int R) {

        int[] count = new int[R + 1]; //加1是为了后续for遍历方便，key = [1, R]
        int N = array.length;
        int[] aux = new int[N];

        for (int i = 0; i < N; i++) {
            count[array[i] + 1]++; //索引从1到R，对于当前的key=array[i]，累加count[key+1]表示有多少个对象比key+1小
        }

        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r]; // 累计计数，得到每个key在aux的起始索引位置
        }

        for (int i = 0; i < N; i++) {
            // key = a[i]应该放到aux的哪个位置，可以从count[key]查到
            // 把key放到这个位置
            // 然后将count[key]往后拨一个位置，以便下次同样的key可以放这里
            aux[count[array[i]]++] = array[i];
        }
    }
}
