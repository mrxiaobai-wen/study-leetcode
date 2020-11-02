package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * <p>
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。
 * 如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * <p>
 * 输出:
 * "apple"
 * 示例 2:
 * <p>
 * 输入:
 * s = "abpcplea", d = ["a","b","c"]
 * <p>
 * 输出:
 * "a"
 * 说明:
 * <p>
 * 所有输入的字符串只包含小写字母。
 * 字典的大小不会超过 1000。
 * 所有输入的字符串长度不会超过 1000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindLongestWord_524 {

    @Test
    public void test() {
        Assert.assertEquals("apple", findLongestWord("abpcplea", Arrays.asList(new String[]{"ale", "apple", "monkey", "plea"})));
        Assert.assertEquals("a", findLongestWord("abpcplea", Arrays.asList(new String[]{"a", "b", "c"})));
    }

    public String findLongestWord(String s, List<String> d) {
        return fun(s, d);
    }

    private String fun(String s, List<String> d) {
        d.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        String result = "";
        for (String str : d) {
            if (str.length() <= result.length()) {
                continue;
            }
            if (check(s, str)) {
                result = str;
            }
        }
        return result;
    }

    private boolean check(String s, String target) {
        if (target.length() > s.length()) {
            return false;
        } else if (target.length() == s.length() && !target.equals(s)) {
            return false;
        }
        char[] sArr = s.toCharArray();
        char[] targetArr = target.toCharArray();
        int sIndex = sArr.length - 1, targetIndex = targetArr.length - 1;
        for (; sIndex >= 0 && targetIndex >= 0; ) {
            if (sArr[sIndex] == targetArr[targetIndex]) {
                targetIndex--;
            }
            sIndex--;
        }
        if (targetIndex >= 0) {
            return false;
        }
        return true;
    }
}
