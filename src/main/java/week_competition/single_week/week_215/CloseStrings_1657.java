package week_competition.single_week.week_215;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 1657. 确定两个字符串是否接近
 * <p>
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
 * <p>
 * 操作 1：交换任意两个 现有 字符。 例如，abcde -> aecdb 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。 例如，aacabb -> bbcbaa（所有 a 转化为 b
 * ，而所有的 b 转换为 a ） 你可以根据需要对任意一个字符串多次使用这两种操作。
 * <p>
 * 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "abc", word2 = "bca" 输出：true 解释：2 次操作从 word1 获得 word2 。 执行操作 1："abc" -> "acb" 执行操作 1："acb" -> "bca" 示例 2：
 * <p>
 * 输入：word1 = "a", word2 = "aa" 输出：false 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。 示例 3：
 * <p>
 * 输入：word1 = "cabbba", word2 = "abbccc" 输出：true 解释：3 次操作从 word1 获得 word2 。 执行操作 1："cabbba" -> "caabbb" 执行操作 2："caabbb"
 * -> "baaccc" 执行操作 2："baaccc" -> "abbccc" 示例 4：
 * <p>
 * 输入：word1 = "cabbba", word2 = "aabbss" 输出：false 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 */
public class CloseStrings_1657 {

    public boolean closeStrings(String word1, String word2) {
        return fun(word1, word2);
    }

    /**
     * 参考思路：
     * <p>
     * 如果两个字符串： 包含的字符种类完全一样； 把各个字符的重复次数放在一个数组里，数组在排序后完全一样； 那么这两个字符串接近。
     * <p>
     * 如果两个字符串长度不一样，那么直接返回false； 遍历两个字符串，用两个长度 2626 的数组存放次数； 同时遍历这两个数组，如果在某下标i处出现一个是0一个不是0（即异或结果是1）的情况，那么直接返回false；
     * 排序后如果数组不相同，也返回false； 否则返回true。
     */
    private boolean fun2(String word1, String word2) {
        if (Objects.equals(word1, word2)) {
            return true;
        } else if (word1.length() != word2.length()) {
            return false;
        }
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            char char1 = word1.charAt(i);
            arr1[char1 - 'a']++;
            char char2 = word2.charAt(i);
            arr2[char2 - 'a']++;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] * arr2[i] == 0 && (arr1[i] != 0 || arr2[i] != 0)) {
                return false;
            }
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean fun(String word1, String word2) {
        if (Objects.equals(word1, word2)) {
            return true;
        } else if (word1.length() != word2.length()) {
            return false;
        }
        Map<Character, Integer> map1 = new HashMap<>();
        for (char temp : word1.toCharArray()) {
            map1.put(temp, map1.getOrDefault(temp, 0));
        }
        Map<Character, Integer> map2 = new HashMap<>();
        for (char temp : word2.toCharArray()) {
            map2.put(temp, map2.getOrDefault(temp, 0));
        }
        if (map1.size() != map2.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : map2.entrySet()) {
            if (entry.getValue() == map1.get(entry.getKey())) {
                map1.remove(entry.getKey());
            }
        }

        return false;
    }
}
