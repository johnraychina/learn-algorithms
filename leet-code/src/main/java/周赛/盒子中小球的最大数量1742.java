package 周赛;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Yi
 */
public class 盒子中小球的最大数量1742 {
    public static void main(String[] args) {

        System.out.println(genKey(23));
        System.out.println(countBalls(5, 15));
    }

    public static int countBalls(int lowLimit, int highLimit) {

        //一根折线，每次进位都会往回折返一个数量级的距离
        //10的5次方，5*9=45，用一个map可以存下来
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(45);

        int maxCnt = 0;
        for (int i = lowLimit; i <= highLimit; i++) {

            Integer key = genKey(i);
            Integer cnt = map.get(key);
            if (cnt == null) { cnt = 0; }
            map.put(key, ++cnt);

            if (maxCnt < cnt) {
                maxCnt = cnt;
            }
        }

        return maxCnt;
    }

    private static Integer genKey(int i) {
        Integer key = 0;
        while (i > 0) {
            int temp = i / 10;
            key += (i - temp * 10);
            i = temp;
        }

        return key;
    }
}

