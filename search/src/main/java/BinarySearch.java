import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author 张义 johnraychina@163.com
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] array = new int[] {-3, 1, 3, 5, 9, 11, 44};

        int key = -10;
        int index = binarySearch(array, key);

        String arrayStr = Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(",", "[", "]"));

        System.out.printf("Array:%s \n", arrayStr);
        System.out.printf("The index of %d is:%d", key, index);
    }

    /**
     * Binary search
     *
     * @param array
     * @param key
     * @return
     */
    public static int binarySearch(int[] array, int key) {
        if (array == null || array.length == 0) {
            return -1;
        }


        return doBinarySearch(key, array, 0, array.length - 1);
    }

    //private 方法内部校验可以放松一点
    //不变的参数放前面，变化的参数放后面
    //相同类型的参数隔开一下，避免传参错位。
    private static int doBinarySearch(int key, int[] array, int lo, int hi) {

        // index of array: lo(w), hi(gh), mid(dle)
        // be careful of the compare conditions
        while (lo <= hi) {

            //目前，CPU中的ALU在算术上只干了两件事，加法，移位，顶多加上取反，在逻辑上，只有与或非异或。
            //加法->加法。
            //减法->取反，加法。
            //乘法->移位，逻辑判断，累加
            //除法->移位，逻辑判断，累减
            //除法在硬件层面目前是用位移和加法实现的 如果没有编译器或者操作系统优化，那么位移比整除效率高
            //int mid = (lo + hi) / 2;
            int mid = (lo + hi) >> 1;
            if (key > array[mid]) {
               lo = mid + 1; //与while lo <= hi 对应，如果偷懒不去加1，则当mid == hi == lo时 会引起死循环
            } else if (key < array[mid]) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }

        //没找到返回负数，遍历完成之后，lo == hi+1, 如果没搜到，lo可以作为key的插入位置(insertion point)
        //当key < array[0], lo不会移动，一直是0, 插入点=-1
        //当key > array[length-1], lo = mid + 1 = length，插入点=length
        //return -1;
        return - (lo + 1);
    }
}
