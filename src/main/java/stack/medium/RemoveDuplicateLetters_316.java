package stack.medium;


/**
 * 316. 去除重复字母
 * <p>
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * <p>
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "bcabc" 输出："abc" 示例 2：
 * <p>
 * 输入：s = "cbacdcbc" 输出："acdb"
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104 s 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-duplicate-letters 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicateLetters_316 {

    public String removeDuplicateLetters(String s) {
        return fun(s);
    }

    /**
     * 方法一：贪心 + 栈 思路与算法
     * <p>
     * 首先考虑一个简单的问题：给定一个字符串 ss，如何去掉其中的一个字符 \textit{ch}ch，使得得到的字符串字典序最小呢？答案是：找出最小的满足 s[i]>s[i+1]s[i]>s[i+1] 的下标 ii，并去除字符
     * s[i]s[i]。为了叙述方便，下文中称这样的字符为「关键字符」。
     * <p>
     * 在理解这一点之后，就可以着手本题了。一个直观的思路是：我们在字符串 ss 中找到「关键字符」，去除它，然后不断进行这样的循环。但是这种朴素的解法会创建大量的中间字符串，我们有必要寻找一种更优的方法。
     * <p>
     * 我们从前向后扫描原字符串。每扫描到一个位置，我们就尽可能地处理所有的「关键字符」。假定在扫描位置 s[i-1]s[i−1] 之前的所有「关键字符」都已经被去除完毕，在扫描字符 s[i]s[i]
     * 时，新出现的「关键字符」只可能出现在 s[i]s[i] 或者其后面的位置。
     * <p>
     * 于是，我们使用栈来维护去除「关键字符」后得到的字符串。如果栈顶字符大于当前字符 s[i]s[i]，说明栈顶字符为「关键字符」，故应当被去除。去除后，新的栈顶字符就与 s[i]s[i] 相邻了，我们继续比较新的栈顶字符与
     * s[i]s[i] 的大小。重复上述操作，直到栈为空或者栈顶字符不大于 s[i]s[i]。
     * <p>
     * 我们还遗漏了一个要求：原字符串 ss 中的每个字符都需要出现在新字符串中，且只能出现一次。为了让新字符串满足该要求，之前讨论的算法需要进行以下两点的更改。
     * <p>
     * 在考虑字符 s[i]s[i] 时，如果它已经存在于栈中，则不能加入字符 s[i]s[i]。为此，需要记录每个字符是否出现在栈中。
     * <p>
     * 在弹出栈顶字符时，如果字符串在后面的位置上再也没有这一字符，则不能弹出栈顶字符。为此，需要记录每个字符的剩余数量，当这个值为 00 时，就不能弹出栈顶字符了。
     */
    private String fun(String str) {
        boolean[] isVis = new boolean[26];
        int[] nums = new int[26];
        for (char cur : str.toCharArray()) {
            nums[cur - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (!isVis[cur - 'a']) {
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > cur) {
                    if (nums[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        isVis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                isVis[cur - 'a'] = true;
                sb.append(cur);
            }
            nums[cur - 'a']--;
        }
        return sb.toString();
    }
}
