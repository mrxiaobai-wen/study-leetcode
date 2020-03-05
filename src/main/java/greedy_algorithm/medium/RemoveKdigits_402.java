package greedy_algorithm.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 402. 移掉K位数字
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * <p>
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * <p>
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class RemoveKdigits_402 {
    public static void main(String[] args) {
        String nums = "10200";
        int k = 1;
        RemoveKdigits_402 ceshi = new RemoveKdigits_402();
        System.out.println(ceshi.removeKdigits(nums, k));
    }

    public String removeKdigits(String num, int k) {
        return fun1(num, k);
    }

    /**
     * 参考官方题解：
     * 思想：高位只要大于其右侧位，就应该被删除；
     * 借助栈
     *
     * @param num
     * @param k
     * @return
     */
    private String fun1(String num, int k) {
        Character[] charArr = new Character[num.length()];
        for (int i = 0; i < num.length(); i++) {
            charArr[i] = num.charAt(i);
        }
        Stack<Character> stack = new Stack<>();
        stack.add(charArr[0]);
        for (int i = 1; i < charArr.length; ) {
            if (k > 0) {
                if (stack.isEmpty() || charArr[i] >= stack.peek()) {
                    stack.add(charArr[i]);
                    i++;
                } else {
                    stack.pop();
                    k--;
                }
            } else {
                stack.add(charArr[i]);
                i++;
            }
        }
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        String result = "";
        while (!stack.isEmpty()) {
            result = stack.pop() + result;
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (char temp : result.toCharArray()) {
            if (flag && temp == '0') {
                continue;
            } else {
                flag = false;
                sb.append(temp);
            }
        }
        result = sb.toString();
        if (result.isEmpty()) {
            return "0";
        }
        return result;
    }
}
