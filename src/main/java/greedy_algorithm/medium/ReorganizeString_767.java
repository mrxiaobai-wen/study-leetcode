package greedy_algorithm.medium;

import org.junit.Test;

import java.util.*;

/**
 * 767. 重构字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * <p>
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 * <p>
 * 输入: S = "aaab"
 * 输出: ""
 * 注意:
 * <p>
 * S 只包含小写字母并且长度在[1, 500]区间内。
 */
public class ReorganizeString_767 {
    @Test
    public void test() {
        System.out.println(reorganizeString("vvvlo"));
    }

    public String reorganizeString(String S) {
        //return fun(S);

        //return fun2(S);

        return fun3(S);
    }

    /**
     * 参考思路：交叉填入
     *
     * @param str
     * @return
     */
    private String fun3(String str) {
        int len = str.length();
        char[] charArr = new char[str.length()];
        int[] countArr = new int[26];
        for (char temp : str.toCharArray()) {
            if (countArr[temp - 'a'] == 0) {
                countArr[temp - 'a'] = 100 + (temp - 'a');
            } else {
                countArr[temp - 'a'] += 100;
            }
            if (countArr[temp - 'a'] / 100 > (len + 1) / 2) {
                return "";
            }
        }
        Arrays.sort(countArr);
        int start = 0;
        for (int i = countArr.length - 1; i >= 0; i--) {
            int size = countArr[i] / 100;
            char curChar = (char) (countArr[i] % 100 + 'a');
            for (; start < len && size > 0; size--) {
                charArr[start] = curChar;
                start += 2;
                if (start >= len) {
                    start = 1;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char temp : charArr) {
            sb.append(temp);
        }
        return sb.toString();
    }

    private String fun2(String str) {
        int len = str.length();
        List<Character> charList = new ArrayList<>();
        char[] countArr = new char[26];
        for (int i = 0; i < len; i++) {
            countArr[str.charAt(i) - 'a']++;
            if (countArr[str.charAt(i) - 'a'] > (len + 1) / 2) {
                return "";
            }
            charList.add(str.charAt(i));
        }
        charList.sort(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1.compareTo(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        char pre = 0;
        for (int i = 0, j = len - 1; i <= j; i++, j--) {
            if (i == j) {
                sb.append(charList.get(i));
                break;
            }
            if (pre == charList.get(i)) {
                sb.append(charList.get(j)).append(charList.get(i));
                pre = charList.get(i);
            } else {
                sb.append(charList.get(i)).append(charList.get(j));
                pre = charList.get(j);
            }
        }

        return sb.toString();
    }

    /**
     * 执行超时
     *
     * @param str
     * @return
     */
    private String fun(String str) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (char temp : str.toCharArray()) {
            if (!charMap.containsKey(temp)) {
                charMap.put(temp, 0);
            }
            charMap.put(temp, charMap.get(temp) + 1);
        }
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>();
        entryList.addAll(charMap.entrySet());
        StringBuilder sb = new StringBuilder();
        char pre = 0;
        while (true) {
            entryList.sort(new Comparator<Map.Entry<Character, Integer>>() {
                @Override
                public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                    return -1 * (o1.getValue() - o2.getValue());
                }
            });
            boolean endFlag = true;
            for (int i = 0; i < entryList.size(); i++) {
                Map.Entry<Character, Integer> entry = entryList.get(i);
                if (entry.getValue() == 0) {
                    if (i == 0) {
                        break;
                    } else {
                        return "";
                    }
                }
                if (entry.getKey() == pre) {
                    continue;
                }
                sb.append(entry.getKey());
                entry.setValue(entry.getValue() - 1);
                pre = entry.getKey();
                break;
            }
        }
    }
}
