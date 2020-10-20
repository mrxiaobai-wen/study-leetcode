package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 424. 替换后的最长重复字符
 *
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
 * 在执行上述操作后，找到包含重复字母的最长子串的长度。
 *
 * 注意:
 * 字符串长度 和 k 不会超过 104。
 *
 * 示例 1:
 *
 * 输入:
 * s = "ABAB", k = 2
 *
 * 输出:
 * 4
 *
 * 解释:
 * 用两个'A'替换为两个'B',反之亦然。
 * 示例 2:
 *
 * 输入:
 * s = "AABABBA", k = 1
 *
 * 输出:
 * 4
 *
 * 解释:
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CharacterReplacement_424 {

    @Test
    public void test() {
        Assert.assertEquals(4,characterReplacement("ABAB",2));
        Assert.assertEquals(4,characterReplacement("AABABBA",1));
    }

    public int characterReplacement(String s, int k) {
        return fun(s,k);
    }

    private int fun(String s,int k) {
        int[] map = new int[26];
        int left = 0,right = 0;
        // 记录曾经出现过的最大符合条件的子串字符长度
        int historyMax = 0;
        char[] arr = s.toCharArray();
        for (;right < arr.length;right++) {
            int index = arr[right] - 'A';
            map[index]++;
            historyMax = Math.max(historyMax,map[index]);
            // 对于 AAABBAAA 且k=2的情况下，这时的historyMax=6,即记录的是6个A的长度
            // 只要此时 right - left + 1 <= 6 + 2,那么这就是一个符合条件的最大长度，
            // 否则此时不符合条件，窗口保留原有大小右移
            if (right - left + 1 > historyMax + k) {
                map[arr[left] - 'A']--;
                left++;
            }
        }
        return right - left;
    }
}
