package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 1616. 分割两个字符串得到回文串
 * <p>
 * 给你两个字符串 a 和 b ，它们长度相同。请你选择一个下标，将两个字符串都在 相同的下标 分割开。
 * 由 a 可以得到两个字符串： aprefix 和 asuffix ，满足 a = aprefix + asuffix ，
 * 同理，由 b 可以得到两个字符串 bprefix 和 bsuffix ，满足 b = bprefix + bsuffix 。
 * 请你判断 aprefix + bsuffix 或者 bprefix + asuffix 能否构成回文串。
 * <p>
 * 当你将一个字符串 s 分割成 sprefix 和 ssuffix 时， ssuffix 或者 sprefix 可以为空。
 * 比方说， s = "abc" 那么 "" + "abc" ， "a" + "bc" ， "ab" + "c" 和 "abc" + "" 都是合法分割。
 * <p>
 * 如果 能构成回文字符串 ，那么请返回 true，否则返回 false 。
 * <p>
 * 请注意， x + y 表示连接字符串 x 和 y 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = "x", b = "y"
 * 输出：true
 * 解释：如果 a 或者 b 是回文串，那么答案一定为 true ，因为你可以如下分割：
 * aprefix = "", asuffix = "x"
 * bprefix = "", bsuffix = "y"
 * 那么 aprefix + bsuffix = "" + "y" = "y" 是回文串。
 * 示例 2：
 * <p>
 * 输入：a = "ulacfd", b = "jizalu"
 * 输出：true
 * 解释：在下标为 3 处分割：
 * aprefix = "ula", asuffix = "cfd"
 * bprefix = "jiz", bsuffix = "alu"
 * 那么 aprefix + bsuffix = "ula" + "alu" = "ulaalu" 是回文串。
 *  
 * 提示：
 * <p>
 * 1 <= a.length, b.length <= 105
 * a.length == b.length
 * a 和 b 都只包含小写英文字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-two-strings-to-make-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CheckPalindromeFormation_1616 {

    @Test
    public void test() {
        Assert.assertEquals(false, checkPalindromeFormation("abda", "acmc"));
        Assert.assertEquals(true, checkPalindromeFormation("x", "y"));
        Assert.assertEquals(true, checkPalindromeFormation("ulacfd", "jizalu"));
        Assert.assertEquals(false, checkPalindromeFormation("aulacfd", "djizalu"));
        Assert.assertEquals(true, checkPalindromeFormation("aejbaalflrmkswrydwdkdwdyrwskmrlfqizjezd",
                "uvebspqckawkhbrtlqwblfwzfptanhiglaabjea"));
    }

    public boolean checkPalindromeFormation(String a, String b) {
        //return fun(a,b);

        return fun2(a, b);
    }

    private boolean fun2(String a, String b) {
        return check(a, b) || check(b, a);
    }

    private boolean check(String a, String b) {
        int m = a.length();
        int i = 0, j = m - 1;
        while (i < j && a.charAt(i) == b.charAt(j)) {
            i++;
            j--;
        }
        int aLeft = i, aRight = j;
        while (aLeft < aRight && a.charAt(aLeft) == a.charAt(aRight)) {
            aLeft++;
            aRight--;
        }
        if (aLeft >= aRight) {
            return true;
        }
        int bLeft = i, bRight = j;
        while (bLeft < bRight && b.charAt(bLeft) == b.charAt(bRight)) {
            bLeft++;
            bRight--;
        }
        if (bLeft >= bRight) {
            return true;
        }
        return false;
    }

    /**
     * 思路错误
     */
    private boolean fun(String a, String b) {
        if (a.length() <= 1) {
            return true;
        }
        char[] arrA = a.toCharArray();
        char[] arrB = b.toCharArray();
        /*for (int left = 0,right = arrB.length - 1;left < right;left++,right--) {
            char temp = arrB[left];
            arrB[left] = arrB[right];
            arrB[right] = temp;
        }*/
        int len = arrA.length;
        for (int i = 0; i < len / 2; i++) {
            if (arrA[i] == arrB[len - 1 - i]) {
                i++;
            } else if (arrA[len - 1 - i] == arrB[i]) {
                i++;
            } else {
                if (i == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }
}
