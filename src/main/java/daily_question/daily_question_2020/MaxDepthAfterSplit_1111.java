package daily_question.daily_question_2020;

import java.util.Stack;

/**
 * 1111. 有效括号的嵌套深度
 * <p>
 * 有效括号字符串 定义：对于每个左括号，都能找到与之对应的右括号，反之亦然。详情参见题末「有效括号字符串」部分。
 * <p>
 * 嵌套深度 depth 定义：即有效括号字符串嵌套的层数。详情参见题末「嵌套深度」部分。
 * <p>
 *  
 * <p>
 * 给你一个「有效括号字符串」 seq，请你将其分成两个不相交的子序列 A 和 B，且 A 和 B 都满足有效括号字符串的定义（注意：A.length + B.length = seq.length）。
 * <p>
 * 由于可能存在多种划分方案，请你从中选出 任意 一组有效括号字符串 A 和 B，使 max(depth(A), depth(B)) 的可能取值最小。其中 depth(A) 表示 A 的嵌套深度，depth(B) 表示 B 的嵌套深度。
 * <p>
 * 请你返回一个长度为 seq.length 的答案数组 answer，编码规则如下：如果 seq[i] 是 A 的一部分，那么 answer[i] = 0。否则，answer[i] = 1。即便有多个满足要求的答案存在，你也只需返回 一个。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：seq = "(()())"
 * 输出：[0,1,1,1,1,0]
 * 示例 2：
 * <p>
 * 输入：seq = "()(())()"
 * 输出：[0,0,0,1,1,0,1,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= text.size <= 10000
 *  
 * <p>
 * 有效括号字符串：
 * <p>
 * 仅由 "(" 和 ")" 构成的字符串，对于每个左括号，都能找到与之对应的右括号，反之亦然。
 * <p>
 * 下述几种情况同样属于有效括号字符串：
 * <p>
 * 空字符串
 * 连接，可以记作 AB（A 与 B 连接），其中 A 和 B 都是有效括号字符串
 * 嵌套，可以记作 (A)，其中 A 是有效括号字符串
 * 嵌套深度：
 * <p>
 * 类似地，我们可以定义任意有效括号字符串 s 的 嵌套深度 depth(S)：
 * <p>
 * s 为空时，depth("") = 0
 * s 为 A 与 B 连接时，depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是有效括号字符串
 * s 为嵌套情况，depth("(" + A + ")") = 1 + depth(A)，其中 A 是有效括号字符串
 * 例如：""，"()()"，和 "()(()())" 都是有效括号字符串，嵌套深度分别为 0，1，2，而 ")(" 和 "(()" 都不是有效括号字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxDepthAfterSplit_1111 {
    public int[] maxDepthAfterSplit(String seq) {
        return fun(seq);
    }

    /**
     * 尽可能的将有效括号平分成两组
     *
     * @param seq
     * @return
     */
    private int[] fun(String seq) {
        int[] result = new int[seq.length()];
        Stack<Character> stack = new Stack<>();
        char[] chars = seq.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.add('(');
                result[i] = stack.size() % 2;
            } else {
                result[i] = stack.size() % 2;
                stack.pop();
            }
        }
        return result;
    }
}
