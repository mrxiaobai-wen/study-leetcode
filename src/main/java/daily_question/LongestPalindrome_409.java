package daily_question;

import java.util.*;

/**
 * 409. 最长回文串
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * <p>
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * <p>
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "abccccdd"
 * <p>
 * 输出:
 * 7
 * <p>
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */
public class LongestPalindrome_409 {
    public int longestPalindrome(String s) {
        return fun(s);
    }

    private int fun(String str) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (char temp : str.toCharArray()) {
            if (!charMap.containsKey(temp)) {
                charMap.put(temp, 0);
            }
            charMap.put(temp, charMap.get(temp) + 1);
        }
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>();
        entryList.addAll(charMap.entrySet());
        entryList.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return -1 * (o1.getValue() - o2.getValue());
            }
        });
        int result = 0;
        for (Map.Entry<Character, Integer> entry : entryList) {
            if (entry.getValue() % 2 == 0) {
                result += entry.getValue();
            } else {
                if (result % 2 == 0) {
                    result += entry.getValue();
                } else {
                    result += entry.getValue() / 2 * 2;
                }
            }
        }
        return result;
    }
}
