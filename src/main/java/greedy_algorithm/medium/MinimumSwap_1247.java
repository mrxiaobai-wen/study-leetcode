package greedy_algorithm.medium;

/**
 * 1247. 交换字符使得字符串相同
 * 有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。
 * <p>
 * 每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。
 * <p>
 * 交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和 s1[j]。
 * <p>
 * 最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s1 = "xx", s2 = "yy"
 * 输出：1
 * 解释：
 * 交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。
 * 示例 2：
 * <p>
 * 输入：s1 = "xy", s2 = "yx"
 * 输出：2
 * 解释：
 * 交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。
 * 交换 s1[0] 和 s2[1]，得到 s1 = "xy"，s2 = "xy" 。
 * 注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。
 * 示例 3：
 * <p>
 * 输入：s1 = "xx", s2 = "xy"
 * 输出：-1
 * 示例 4：
 * <p>
 * 输入：s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s1.length, s2.length <= 1000
 * s1, s2 只包含 'x' 或 'y'。
 */
public class MinimumSwap_1247 {
    public int minimumSwap(String s1, String s2) {
        return fun1(s1, s2);
    }

    /**
     * 参考代码：
     * 思路：当s1和s2不同的时候，肯定一个为x，一个为y.我们已S1位基准，分别统计x\y不同的对数。
     * 只要两种数量加在一起能够等于偶数，那么就可以将所有的拼成两两一对，就可以交换成功，否则交换失败。
     *
     * @param s1
     * @param s2
     * @return
     */
    private int fun1(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return -1;
        }
        int countX = 0;
        int countY = 0;
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] == arr2[i]) {
                continue;
            } else if (arr1[i] == 'x') {
                countX++;
            } else {
                countY++;
            }
        }
        if ((countX + countY) % 2 == 1) {
            return -1;
        }
        /**
         * 之所以加1：
         * 当xx-yy或者yy-xx这样只用交换一次，所以我们尽量拼成这样；
         * 当为奇数的时候，及剩下一对xy-yx或者yx-xy，这样就需要交换两次。
         */
        return (countX + 1) / 2 + (countY + 1) / 2;
    }
}
