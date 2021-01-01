package daily_question.daily_question_2020;

import java.util.HashMap;
import java.util.Map;

/**
 * 1160. 拼写单词
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * <p>
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * <p>
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 * <p>
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 * 示例 2：
 * <p>
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * 所有字符串中都仅包含小写英文字母
 */
public class CountCharacters_1160 {
    public int countCharacters(String[] words, String chars) {
        return fun(words, chars);
    }

    private int fun(String[] words, String chars) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (char temp : chars.toCharArray()) {
            if (!charMap.containsKey(temp)) {
                charMap.put(temp, 0);
            }
            charMap.put(temp, charMap.get(temp) + 1);
        }
        int result = 0;
        Map<Character, Integer> tempMap = new HashMap<>();
        for (String word : words) {
            tempMap.clear();
            for (char wChar : word.toCharArray()) {
                if (!tempMap.containsKey(wChar)) {
                    tempMap.put(wChar, 0);
                }
                tempMap.put(wChar, tempMap.get(wChar) + 1);
            }
            boolean flag = true;
            for (Map.Entry<Character, Integer> entry : tempMap.entrySet()) {
                if (charMap.get(entry.getKey()) == null || charMap.get(entry.getKey()) < entry.getValue()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result += word.length();
            }
        }
        return result;
    }
}
