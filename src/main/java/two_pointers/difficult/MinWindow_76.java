package two_pointers.difficult;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 *
 * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * 示例：
 *
 * 输入：S = "ADOBECODEBANC", T = "ABC"
 * 输出："BANC"
 *
 * 提示：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinWindow_76 {

    @Test
    public void test() {
        Assert.assertEquals("BANC",minWindow("ADOBECODEBANC", "ABC"));
        Assert.assertEquals("",minWindow("ADOBECODEBANC", "ABZ"));
    }

    public String minWindow(String s, String t) {
        return fun(s,t);
    }

    private String fun(String s,String t) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (t == null || t.length() == 0 || t.length() > s.length()) {
            return "";
        }
        Map<Character,Integer> targetMap = new HashMap<>();
        for (char temp : t.toCharArray()) {
            targetMap.put(temp,targetMap.getOrDefault(temp,0) + 1);
        }
        Map<Character,Integer> tempMap = new HashMap<>();
        int tLen = t.length();
        int sLen = s.length();
        int maxLen = Integer.MAX_VALUE;
        int resultLeft = -1,resultRight = -1;
        for (int left = 0,right = 0;left <= right && right <= sLen;) {
            if (check(targetMap,tempMap)) {
                // 已经匹配，考虑缩小范围
                if (right - left == tLen) {
                    return s.substring(left,right);
                } else if (maxLen > right - left){
                    maxLen = right - left;
                    resultLeft = left;
                    resultRight = right;
                }
                char leftChar = s.charAt(left);
                tempMap.put(leftChar,tempMap.getOrDefault(leftChar,0) - 1);
                if (tempMap.getOrDefault(leftChar,0) <= 0) {
                    tempMap.remove(leftChar);
                }
                left++;
            } else {
                // 不匹配，继续扩大范围
                if (right < sLen) {
                    char cur = s.charAt(right);
                    tempMap.put(cur,tempMap.getOrDefault(cur,0) + 1);
                }
                right++;
            }
        }
        if (resultLeft >= 0 && resultRight >= 0) {
            return s.substring(resultLeft,resultRight);
        }
        return "";
    }

    private boolean check(Map<Character,Integer> targetMap,Map<Character,Integer> tempMap) {
        for (Map.Entry<Character, Integer> entry : targetMap.entrySet()) {
            if (entry.getValue() > tempMap.getOrDefault(entry.getKey(),0)) {
                return false;
            }
        }
        return true;
    }
}
