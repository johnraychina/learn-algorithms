package 状态机;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 65. 有效数字
 * 有效数字（按顺序）可以分成以下几个部分：
 *
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @hint DFA 有限状态自动机
 */
public class 有效数字65 {


    private static Map<State, Map<CharType, State>> stateTransferMap = new HashMap<>() {{
        put(State.INIT, new HashMap<>() {{
            put(CharType.CHAR_SIGN, State.SIGN);
            put(CharType.CHAR_NUM, State.INT_NUM);
            put(CharType.CHAR_POINT, State.NO_NUM_POINT);
        }});

        put(State.SIGN, new HashMap<>() {{
            put(CharType.CHAR_NUM, State.INT_NUM);
            put(CharType.CHAR_POINT, State.NO_NUM_POINT);
        }});

        put(State.INT_NUM, new HashMap<>() {{
            put(CharType.CHAR_NUM, State.INT_NUM);
            put(CharType.CHAR_POINT, State.NUM_POINT);
            put(CharType.CHAR_EXP, State.EXP);
        }});

        put(State.NUM_POINT, new HashMap<>() {{
            put(CharType.CHAR_NUM, State.FRACTION);
            put(CharType.CHAR_EXP, State.EXP);
        }});

        put(State.NO_NUM_POINT, new HashMap<>() {{
            put(CharType.CHAR_NUM, State.FRACTION);
        }});

        put(State.FRACTION, new HashMap<>() {{
            put(CharType.CHAR_EXP, State.EXP);
            put(CharType.CHAR_NUM, State.FRACTION);
        }});

        put(State.EXP, new HashMap<>() {{
            put(CharType.CHAR_SIGN, State.EXP_SIGN);
            put(CharType.CHAR_NUM, State.EXP_NUM);
        }});
        put(State.EXP_SIGN, new HashMap<>() {{
            put(CharType.CHAR_NUM, State.EXP_NUM);
        }});
        put(State.EXP_NUM, new HashMap<>() {{
            put(CharType.CHAR_NUM, State.EXP_NUM);
        }});

    }};

    public static boolean isNumber(String s) {

        State state = State.INIT;

        for (int i = 0; i < s.length(); i++) {
            Map<CharType, State> stateMap = stateTransferMap.get(state);
            if (stateMap == null) return false;
            state = stateMap.get(toCharType(s.charAt(i)));
            if (state == null) return false;
        }

        return state == State.INIT
                || state == State.INT_NUM
                || state == State.NUM_POINT
                || state == State.FRACTION
                || state == State.EXP_NUM;
    }

    private static CharType toCharType(char c) {
        if (c == '+' || c == '-') return CharType.CHAR_SIGN;
        if (c == '.') return CharType.CHAR_POINT;
        if (c == 'e' || c == 'E') return CharType.CHAR_EXP;
        if ('0' <= c && c <= '9') return CharType.CHAR_NUM;
        return CharType.CHAR_ILLEGAL;
    }

    enum CharType {
        CHAR_SIGN,
        CHAR_NUM,
        CHAR_POINT,
        CHAR_EXP,
        CHAR_ILLEGAL
    }

    enum State {
        /**
         * 初始状态
         * 符号位
         * 整数部分
         * 左侧有整数的小数点
         * 左侧无整数的小数点（根据前面的第二条额外规则，需要对左侧有无整数的两种小数点做区分）
         * 小数部分
         * 字符 e
         * 指数部分的符号位
         * 指数部分的整数部分
         */
        INIT,
        SIGN,
        INT_NUM,
        NUM_POINT,
        NO_NUM_POINT,
        FRACTION,
        EXP,
        EXP_SIGN,
        EXP_NUM
    }


    public static void main(String[] args) {
//        System.out.println(isNumber("-.1234e4"));
//        System.out.println(isNumber("-1.1234e4"));
//        System.out.println(isNumber("-.e4"));
//        System.out.println(isNumber("3.5e+3.5e+3.5"));
        System.out.println(isNumber("005047e+6"));
    }
}
