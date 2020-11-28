package recursion.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 395. 至少有K个重复字符的最长子串
 * <p>
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
 * <p>
 * 示例 1: 输入: s = "aaabb", k = 3 输出: 3 最长子串为 "aaa" ，其中 'a' 重复了 3 次。 示例 2: 输入: s = "ababbc", k = 2 输出: 5
 * <p>
 * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubstring_395 {

    @Test
    public void test() {
        Assert.assertEquals(3, longestSubstring("aaabb", 3));
        Assert.assertEquals(5, longestSubstring("ababbc", 2));
    }

    public int longestSubstring(String s, int k) {
        return fun(s, k);
    }

    public int fun(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char temp : s.toCharArray()) {
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            if (map.get(sb.charAt(i)) < k) {
                sb.setCharAt(i, ',');
            }
        }
        String[] arr = sb.toString().split(",");
        if (arr.length == 1) {
            return arr[0].length();
        }
        int max = 0;
        for (String str : arr) {
            max = Math.max(max, longestSubstring(str, k));
        }
        return max;
    }

}
