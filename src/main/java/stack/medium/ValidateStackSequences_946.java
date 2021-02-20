package stack.medium;

import java.util.Stack;

/**
 * 946. 验证栈序列
 * <p>
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 * <p>
 * 示例 1： 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1] 输出：true 解释：我们可以按以下顺序执行： push(1), push(2), push(3), push(4), pop()
 * -> 4, push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * <p>
 * 示例 2： 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2] 输出：false 解释：1 不能在 2 之前弹出。
 * <p>
 * 提示： 0 <= pushed.length == popped.length <= 1000 0 <= pushed[i], popped[i] < 1000 pushed 是 popped 的排列。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/validate-stack-sequences 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidateStackSequences_946 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        return fun(pushed, popped);
    }

    /**
     * 参考思路：
     * <p>
     * 方法一： 贪心
     * <p>
     * 思路 所有的元素一定是按顺序 push 进去的，重要的是怎么 pop 出来？ 假设当前栈顶元素值为 2，同时对应的 popped 序列中下一个要 pop 的值也为 2，那就必须立刻把这个值 pop 出来。因为之后的 push
     * 都会让栈顶元素变成不同于 2 的其他值， 这样再 pop 出来的数 popped 序列就不对应了。
     * <p>
     * 算法 将 pushed 队列中的每个数都 push 到栈中，同时检查这个数是不是 popped 序列中下一个要 pop 的值，如果是就把它 pop 出来。 最后，检查不是所有的该 pop 出来的值都是 pop 出来了。
     */
    private boolean fun(int[] pushed, int[] popped) {
        int len = popped.length;
        Stack<Integer> stack = new Stack<>();
        int n = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[n]) {
                stack.pop();
                n++;
            }
        }
        return n == len;
    }
}
