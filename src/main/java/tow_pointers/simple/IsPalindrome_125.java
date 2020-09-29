package tow_pointers.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 125. 验证回文串
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsPalindrome_125 {

    @Test
    public void test() {
        Assert.assertEquals(true,isPalindrome("A man, a plan, a canal: Panama"));
        Assert.assertEquals(false,isPalindrome("race a car"));
    }

    public boolean isPalindrome(String s) {
        return fun(s);
    }

    private boolean fun(String s) {
        char[] arr = s.toLowerCase().toCharArray();
        int index1 = 0;
        int index2 = arr.length - 1;
        while (index2 > index1) {
            char char1 = arr[index1];
            char char2 = arr[index2];
            if ((char1 < 'a' || char1 > 'z') && (char1 < '0' || char1 > '9')) {
                index1++;
            } else if ((char2 < 'a' || char2 > 'z') && (char2 < '0' || char2 > '9')) {
                index2--;
            } else if (arr[index2] != arr[index1]) {
                return false;
            } else {
                index2--;
                index1++;
            }
        }
        return true;
    }
}
