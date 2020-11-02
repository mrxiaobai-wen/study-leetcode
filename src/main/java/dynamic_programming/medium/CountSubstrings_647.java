package dynamic_programming.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 647. 回文子串
 * <p>
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 * <p>
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * 注意:
 * <p>
 * 输入的字符串长度不会超过1000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountSubstrings_647 {

    public int countSubstrings(String s) {
        return 0;
    }

    private int fun(String str) {
        if (str == null) {
            return 0;
        } else if (str.length() <= 0) {
            return str.length();
        }
        char[] arr = str.toCharArray();
        int len = arr.length;
        Set<String> resultSet = new HashSet<>();
        for (int cur = 0; cur < len; cur++) {
            resultSet.add(cur + "-" + cur);
            for (int i = cur - 1, j = cur + 1; i >= 0 && j < len; i--, j++) {
                if (arr[i] == arr[j]) {
                    resultSet.add(i + "-" + j);
                } else {
                    break;
                }
            }
            for (int i = cur, j = cur + 1; i >= 0 && j < len; i--, j++) {
                if (arr[i] == arr[j]) {
                    resultSet.add(i + "-" + j);
                } else {
                    break;
                }
            }
            for (int i = cur - 1, j = cur; i >= 0 && j < len; i--, j++) {
                if (arr[i] == arr[j]) {
                    resultSet.add(i + "-" + j);
                } else {
                    break;
                }
            }
        }
        return resultSet.size();
    }
}
