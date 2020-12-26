/**
 * 牛顿法求平方根，1-2-4-8... 精度呈平方级别增加
 * <p>
 * https://www.youtube.com/watch?v=eCaXlAaN2uE&list=PLUl4u3cNGP61Oq3tWYp6V_F-5jb5L2iHb&index=11
 * https://www.zhihu.com/question/20690553
 * <pre>
 *      X_i+1 = X_i / 2 + a/(2*X_i)
 * </pre>
 * 迭代次数：logX
 *
 * @author Zhang Yi
 */
public class NewtonSquareRoot {

    public static void main(String[] args) {

        //求sqrt(2), sqrt(3)
        //Math.sqrt(2.0)
        sqrt(2);
        System.out.println("==========" + Math.sqrt(2));
        sqrt(3);
        System.out.println("==========" + Math.sqrt(3));

    }

    public static double sqrt(double a) {
        if (a <= 0) {
            throw new IllegalArgumentException();
        }

        double x = a; //好的初始位置可以减少一些迭代次数
        double halfA = a / 2;
        for (int i = 0; i < 10; i++) {
            x = x / 2 + halfA / x; //Newtons' method
            System.out.printf("%d th iteration of sqrt(%f) = %f \n", i, a, x);
        }

        return x;
    }
}
