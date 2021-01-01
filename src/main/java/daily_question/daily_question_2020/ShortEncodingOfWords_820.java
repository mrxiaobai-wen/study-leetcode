package daily_question.daily_question_2020;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 820. 单词的压缩编码
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 * <p>
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 * <p>
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 * <p>
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母 。
 */
public class ShortEncodingOfWords_820 {
    public int minimumLengthEncoding(String[] words) {
        return fun1(words);
    }

    /**
     * 方法一：存储后缀
     * <p>
     * 思路
     * <p>
     * 如果单词 X 是 Y 的后缀，那么单词 X 就不需要考虑了，因为编码 Y 的时候就同时将 X 编码了。
     * 例如，如果 words 中同时有 "me" 和 "time"，我们就可以在不改变答案的情况下不考虑 "me"。
     * <p>
     * 如果单词 Y 不在任何别的单词 X 的后缀中出现，那么 Y 一定是编码字符串的一部分。
     * <p>
     * 因此，目标就是保留所有不是其他单词后缀的单词，最后的结果就是这些单词长度加一的总和，因为每个单词编码后后面还需要跟一个 # 符号。
     *
     * @param words
     * @return
     */
    private int fun1(String[] words) {
        Set<String> tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(words));
        for (String str : words) {
            for (int i = 1; i < str.length(); i++) {
                tempSet.remove(str.substring(i));
            }
        }
        int result = 0;
        for (String temp : tempSet) {
            result += temp.length() + 1;
        }
        return result;
    }
}
