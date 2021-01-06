package stack.easy;

import java.util.Stack;

/**
 * 1544. 整理字符串
 * <p>
 * 给你一个由大小写英文字母组成的字符串 s 。 一个整理好的字符串中，两个相邻字符 s[i] 和 s[i+1]，其中 0<= i <= s.length-2 ，要满足如下条件:
 * <p>
 * 若 s[i] 是小写字符，则 s[i+1] 不可以是相同的大写字符。 若 s[i] 是大写字符，则 s[i+1] 不可以是相同的小写字符。 请你将字符串整理好，每次你都可以从字符串中选出满足上述条件的 两个相邻
 * 字符并删除，直到字符串整理好为止。
 * <p>
 * 请返回整理好的 字符串 。题目保证在给出的约束条件下，测试样例对应的答案是唯一的。
 * <p>
 * 注意：空字符串也属于整理好的字符串，尽管其中没有任何字符。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "leEeetcode" 输出："leetcode" 解释：无论你第一次选的是 i = 1 还是 i = 2，都会使 "leEeetcode" 缩减为 "leetcode" 。 示例 2：
 * <p>
 * 输入：s = "abBAcC" 输出："" 解释：存在多种不同情况，但所有的情况都会导致相同的结果。例如： "abBAcC" --> "aAcC" --> "cC" --> "" "abBAcC" --> "abBA" -->
 * "aA" --> "" 示例 3：
 * <p>
 * 输入：s = "s" 输出："s"
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 100 s 只包含小写和大写英文字母
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/make-the-string-great 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MakeTheStringGreat_1544 {

    public String makeGood(String s) {
        return fun(s);
    }

    /**
     * 参考方法：模拟
     */
    public String fun2(String s) {
        StringBuffer ret = new StringBuffer();
        int retIndex = -1;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (ret.length() > 0 && Character.toLowerCase(ret.charAt(retIndex)) == Character.toLowerCase(ch) &&
                    ret.charAt(retIndex) != ch) {
                ret.deleteCharAt(retIndex);
                retIndex--;
            } else {
                ret.append(ch);
                retIndex++;
            }
        }
        return ret.toString();
    }

    private String fun(String s) {
        Stack<Character> stack = new Stack<>();
        for (char temp : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(temp);
            } else {
                if ((stack.peek() ^ temp) == 32) {
                    stack.pop();
                } else {
                    stack.push(temp);
                }
            }
        }
        String result = "";
        while (!stack.isEmpty()) {
            result = stack.pop() + result;
        }
        return result;
    }
}
