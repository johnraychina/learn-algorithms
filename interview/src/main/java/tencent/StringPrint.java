package tencent;

/**
 * @author Zhang Yi
 */
public class StringPrint {

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        System.out.println("Hello World!");

        String input = "12345";

        //给定一个字符串，输出第一个，删除
        char[] output = new char[input.length()];
        int curIndex = 0;
        int index = 0;
        while (index < output.length) {
            output[index++] = input.charAt(curIndex++);

            //下个字符复制到尾部
            if (curIndex < input.length()) {
                input = input + input.substring(curIndex, curIndex + 1);
                curIndex++;
            }
        }

        String result = new String(output);
        System.out.println(result);

    }

}
