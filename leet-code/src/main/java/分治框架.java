import java.util.Arrays;

/**
 * @author Zhang Yi
 */
public class 分治框架 {

    public <T, R> Object solve(T problem) {
        //divide拆分为多个子问题
        T[] subProblems = divide(problem);

        return Arrays.stream(subProblems)
            //solve子问题再向下递归求解
            .map(sub -> solve(sub))
            //merge合并子问题结果
            .reduce((r1, r2) -> merge(r1, r2));

    }

    private <T> T[] divide(T problem) {
        return null;
    }

    private <R> R merge(R r1, R r2) {
        return null;
    }

}
