import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * Goal: split text into "good" lines
 * • obvious (MS Word/Open Office) algorithm: put as many words that fit on first line, repeat
 * • but this can make very bad lines
 *
 *
 * • Define badness(i, j) for line of words[i : j].
 * For example, ∞ if total length > page width, else (page width − total length)^3.
 * • goal: split words into lines to min 􏰇 badness
 * </pre>
 *
 * @author Zhang Yi
 */
public class TestJustification {

    private static final int LINE_WIDTH = 80;

    public static void main(String[] args) throws IOException, URISyntaxException {
        // 1.子问题：前面分了行之后，剩下[i:]的单词如何分行,子问题变为后缀问题

        // 2.猜想：在哪分行[i,j]，j=从i+1到n，有n-i个可选项
        //dp[i] 表示i的后缀text[i:]的badness
        //
        //dp[n]=0 后面没有单词了，所以badness=0
        //dp[i] = min{ badness(i,j) + dp(j), for j in [i+1, n] }

        //遍历i=[n : 0]求得外层问题
        //遍历j=[i+1, n]求得内层子问题dp[i]
        //时间复杂度 n(n-1)/2 ~ O(n^2)

        URI uri = TestJustification.class.getClassLoader().getResource("text.txt").toURI();
        List<String> lines = Files.readAllLines(Paths.get(uri));
        String text = String.join(" ", lines);

        String[] words = text.split(" ");
        int n = words.length;

        int[] dp = new int[n + 1];
        Set<Integer> breakIndex = new HashSet<>();
        //初始条件
        dp[n] = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = Integer.MAX_VALUE; //对应选择函数min
        }

        for (int i = n - 1; i >= 0; i--) {
            int minBadnessArgJ = i + 1; //记录使得badness[i, j)最小的j
            for (int j = i + 1; j <= n; j++) {
                if (dp[j] + badness(words, i, j) < dp[i]) {
                    dp[i] = dp[j] + badness(words, i, j);
                    minBadnessArgJ = j;//todo 
                }
            }
            breakIndex.add(minBadnessArgJ);
            System.out.printf("i=%d, minBadnessArgJ=%d \n", i, minBadnessArgJ);
        }

        //结果：
        // dp[0]代表word[0:j) 的 min{badness(0,j), for j =[0+1,n)}, 其中j为最佳切分点（exclusive)
        // dp[i]代表word[i:j) 的 min{badness(i,j), for j =[i+1,n)}, 其中j为最佳切分点（exclusive)
        // breakIndex代表最佳切分点位
        StringBuilder textBuilder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            textBuilder.append(words[i]);
            if (breakIndex.contains(i)) {
                textBuilder.append("\n\r");
            } else {
                textBuilder.append(" ");
            }
        }

        System.out.println(textBuilder);
    }

    public static int badness(String[] words, int i, int j) {

        int wordLength = 0;
        for (int k = i; k < j; k++) {
            wordLength += words[k].length();
            wordLength++; //空格
        }

        int availableSpaces = LINE_WIDTH - wordLength;
        if (availableSpaces < 0) {
            return Integer.MAX_VALUE;
        } else {
            return availableSpaces * availableSpaces * availableSpaces;
        }
    }
}
