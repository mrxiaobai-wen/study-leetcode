package daily_question;

import java.util.Stack;

/**
 * 151. 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * <p>
 * 说明：
 * <p>
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 */
public class ReverseWordsInAString_151 {
    public String reverseWords(String s) {
        return fun(s);
    }

    private String fun(String s) {
        s = s.trim();
        String result = "";
        int i = 0, j = 0;
        char blank = ' ';
        while (i < s.length()) {
            if (s.charAt(i) == blank) {
                i++;
                continue;
            }
            j = s.indexOf(" ", i + 1);
            if (j < 0) {
                result = s.substring(i) + result;
                break;
            } else {
                result = " " + s.substring(i, j) + result;
            }
            i = j + 1;
        }
        return result;
    }
}
