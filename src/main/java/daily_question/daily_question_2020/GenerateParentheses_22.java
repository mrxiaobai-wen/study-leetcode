package daily_question.daily_question_2020;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateParentheses_22 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        fun(n, n, "", result);
        return result;
    }

    private void fun(int left, int right, String str, List<String> container) {
        if (right < left) {
            return;
        }
        if (right == 0) {
            container.add(str);
            return;
        }
        if (left == 0) {
            fun(left, right - 1, str + ")", container);
            return;
        }
        fun(left - 1, right, str + "(", container);
        fun(left, right - 1, str + ")", container);
    }
}
