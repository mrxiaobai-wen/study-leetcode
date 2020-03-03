package greedy_algorithm.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。
 * <p>
 * 给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
 * <p>
 * 返回可以通过分割得到的平衡字符串的最大数量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "RLRRLLRLRL"
 * 输出：4
 * 解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
 * 示例 2：
 * <p>
 * 输入：s = "RLLLLRRRLR"
 * 输出：3
 * 解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。
 * 示例 3：
 * <p>
 * 输入：s = "LLLLRRRR"
 * 输出：1
 * 解释：s 只能保持原样 "LLLLRRRR".
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s[i] = 'L' 或 'R'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-a-string-in-balanced-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BalancedStringSplit_1221 {
    public static void main(String[] args) {
        String str = "RLRRLLRLRL";
        BalancedStringSplit_1221 ceshi = new BalancedStringSplit_1221();
        System.out.println(ceshi.balancedStringSplit(str));
    }

    public int balancedStringSplit(String s) {
        //return fun1(s);
        return fun2(s);
    }

    /**
     * 思路：将字符串按照RL分别进行分段统计，然后从左到右进行匹配
     * </p>
     * 题目有歧义
     *
     * @param s
     * @return
     */
    private int fun1(String s) {
        List<Integer> list = new ArrayList<>();
        char currentChar = 0;
        int currentNum = 0;
        for (char tempChar : s.toCharArray()) {
            if (currentChar == 0) {
                currentChar = tempChar;
                currentNum = 1;
                continue;
            }
            if (tempChar == currentChar) {
                currentNum++;
            } else {
                list.add(currentNum);
                currentChar = tempChar;
                currentNum = 1;
            }
        }
        list.add(currentNum);
        if (list.size() <= 1) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) == 0) {
                continue;
            }
            if (list.get(i) == list.get(i + 1)) {
                list.set(i + 1, 0);
                result++;
            } else if (list.get(i) < list.get(i + 1)) {
                list.set(i + 1, list.get(i + 1) - list.get(i));
                result++;
            }
        }
        return result;
    }

    /**
     * 参考代码
     *
     * @param s
     */
    private int fun2(String s) {
        int result = 0;
        int rCount = 0;
        for (char temp : s.toCharArray()) {
            if (temp == 'R') {
                rCount++;
            } else {
                rCount--;
            }
            if (rCount == 0) {
                result++;
            }
        }
        return result;
    }
}
