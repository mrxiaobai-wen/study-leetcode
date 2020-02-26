package greedy_algorithm.simple;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * <p>
 * 返回 true.
 * <p>
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * <p>
 * 返回 false.
 * <p>
 * 后续挑战 :
 * <p>
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsSubsequence_392 {
    public static void main(String[] args) {
        IsSubsequence_392 ceshi = new IsSubsequence_392();
        System.out.println(ceshi.isSubsequence("abc", ""));
    }

    /**
     * TODO 使用动态规划的方式解决
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        //return fun1(s,t);

        return fun2(s, t);
    }

    /**
     * 暴力搜寻法！！！
     * 设置一基础指针，从前往后遍历字符串S。
     * 设置两个快速指针，一个快速指针从基础指针出发，遍历串S；另一个快速指针从0出发，遍历T串。若T串遍历出头，则为S的子串。
     * 若S快速指针遍历完且T未遍历完，则表示不成立。基础指针前移一位，继续上面的操作。
     *
     * @param s 子串
     * @param t 目标串
     */
    private boolean fun1(String s, String t) {
        /*if (s.length() == 0 && t.length() == 0) {
            return true;
        }
        if (t.length() == 0) {
            return false;
        } else if (s.length() == 0) {
            return true;
        }
        int i = 0,j = 0;
        for (int basic = 0;basic <= t.length() - s.length();basic++) {
            int indexS = 0;
            for (int indexT = basic;indexT < t.length() && indexS < s.length();indexT++) {
                if (t.charAt(indexT) == s.charAt(indexS)) {
                    indexS++;
                }
            }
            if (indexS >= s.length()) {
                return true;
            }
        }
        return false;*/
        // 因为回退导致执行超时
        // 因为子序列并不要求是连续分布在目标串中的。所以对目标串的搜寻不需要回退。
        // 参考代码
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            while (j < t.length() && s.charAt(i) != t.charAt(j)) {
                j++;
            }
            i++;
            j++;
        }
        if (i >= s.length() && j <= t.length()) {
            return true;
        }
        return false;
    }

    /**
     * 参考代码：
     * 使用Java库
     *
     * @param s
     * @param t
     * @return
     */
    private boolean fun2(String s, String t) {
        int j = -1;
        for (int i = 0; i < s.length(); i++) {
            j = t.indexOf(s.charAt(i), j + 1);
            if (j == -1) {
                return false;
            }
        }
        return true;
    }
}
