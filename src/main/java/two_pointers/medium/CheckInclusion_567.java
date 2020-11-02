package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 567. 字符串的排列
 * <p>
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * <p>
 * 示例2:
 * <p>
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * 注意：
 * <p>
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CheckInclusion_567 {

    @Test
    public void test() {
        Assert.assertEquals(true, checkInclusion("ab", "eidbaooo"));
        Assert.assertEquals(false, checkInclusion("ab", "eidboaoo"));
    }

    public boolean checkInclusion(String s1, String s2) {
        return fun(s1, s2);
    }

    private boolean fun(String s1, String s2) {
        if (s1 == null || s1.length() <= 0) {
            return true;
        }
        if (s2 == null || s2.length() <= 0) {
            return false;
        }
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] s1Arr = new int[26];
        for (char temp : s1.toCharArray()) {
            s1Arr[temp - 'a']++;
        }
        int[] s2Arr = new int[26];
        for (int left = 0, right = 0; right < s2.length(); right++) {
            char curRight = s2.charAt(right);
            s2Arr[curRight - 'a']++;
            if (right - left + 1 > s1.length()) {
                char curLeft = s2.charAt(left);
                s2Arr[curLeft - 'a']--;
                left++;
            }
            if (checkMatch(s1Arr, s2Arr)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkMatch(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
