package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LengthOfLongestSubstring_3 {

    @Test
    public void test() {
        Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"));
    }

    public int lengthOfLongestSubstring(String s) {
        return fun(s);
    }

    /**
     * 滑动窗口
     */
    private int fun2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        } else if (str.length() == 1) {
            return 1;
        }
        char[] arr = str.toCharArray();
        int result = 0;
        int len = arr.length;
        int right = -1;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                set.remove(arr[i - 1]);
            }
            while (right + 1 < len && !set.contains(arr[right + 1])) {
                // 不断右移右指针
                set.add(arr[right + 1]);
                right++;
            }
            // 第i到right个字符是一个机场的无重复字符子串
            result = Math.max(result, set.size());
        }
        return result;
    }

    private int fun(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        } else if (str.length() == 1) {
            return 1;
        }
        char[] arr = str.toCharArray();
        int result = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            Set<Character> set = new HashSet<>();
            set.add(arr[i]);
            for (int j = i + 1; j < arr.length; j++) {
                if (!set.contains(arr[j])) {
                    set.add(arr[j]);
                } else {
                    break;
                }
            }
            result = Math.max(result, set.size());
        }
        return result;
    }
}
