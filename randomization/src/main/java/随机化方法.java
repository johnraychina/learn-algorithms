
/**
 * <pre>
 * https://zhuanlan.zhihu.com/p/73147939
 *
 * 公平的洗牌算法，应该能等概率地给出这n!个结果中的任意一个。
 * 但是O(2^n) 已经被称为指数爆炸了。O(n!) 不可想象。
 * 所以，这个算法确实是公平的，但是，时间不可容忍。
 *
 * 换个角度思考"公平"，其实我们也可以认为，公平是指：
 * 对于生成的排列，每个元素都能等概率地出现在每一个位置。
 * 或者反过来，每个位置都能等概率地放置每个元素。
 *
 * </pre>
 *
 * @author Zhang Yi
 */
public class 随机化方法 {

    // Knuth 洗牌算法。
    public static void shuffle(int a[]) {
        for (int i = a.length - 1; i >= 0; i--) {
            // todo 注意：如果这里随机数乘i,则会导致i=0,1时都为0，从而导致不公平，所以需要加1
            int pos = (int)(Math.random() * (i + 1));
            swap(a, i, pos);
        }
    }

    public static void swap(int a[], int i, int j) {
        int t = a[j];
        a[j] = a[i];
        a[i] = t;
    }
}
