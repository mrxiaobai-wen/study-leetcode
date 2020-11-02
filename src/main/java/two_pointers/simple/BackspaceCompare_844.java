package two_pointers.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 844. 比较含退格的字符串
 * <p>
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * <p>
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * <p>
 * 示例 1：
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * <p>
 * 示例 2：
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * <p>
 * 示例 3：
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * <p>
 * 示例 4：
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 *  
 * <p>
 * 进阶：
 * <p>
 * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BackspaceCompare_844 {

    @Test
    public void test() {
        Assert.assertEquals(true, backspaceCompare("ab#c", "ad#c"));
        Assert.assertEquals(true, backspaceCompare("ab##", "c#d#"));
        Assert.assertEquals(true, backspaceCompare("a##c", "#a#c"));
        Assert.assertEquals(false, backspaceCompare("a#c", "b"));
    }

    public boolean backspaceCompare(String S, String T) {
        return fun(S, T);
    }

    private boolean fun(String s, String t) {
        char[] arr1 = s.toCharArray();
        int len1 = handleArr(arr1);
        char[] arr2 = t.toCharArray();
        int len2 = handleArr(arr2);
        if (len1 != len2) {
            return false;
        }
        for (int i = 0; i < len1; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    private int handleArr(char[] arr) {
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            char cur = arr[i];
            if (cur == '#') {
                len--;
                if (len < 0) {
                    len = 0;
                }
            } else {
                arr[len] = arr[i];
                len++;
            }
        }
        return len;
    }
}
