package stack.medium;

import java.util.Stack;

/**
 * 456. 132模式
 * <p>
 * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法， 当给定有 n
 * 个数字的序列时，验证这个序列中是否含有132模式的子序列。 注意：n 的值小于15000。
 * <p>
 * 示例1: 输入: [1, 2, 3, 4] 输出: False 解释: 序列中不存在132模式的子序列。
 * <p>
 * 示例 2: 输入: [3, 1, 4, 2] 输出: True 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
 * <p>
 * 示例 3: 输入: [-1, 3, 2, 0] 输出: True 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/132-pattern 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Find132pattern_456 {

    public boolean find132pattern(int[] nums) {

        return fun1(nums);
    }

    /**
     * 普通遍历
     * </p>
     * 想太简单，要求子序列
     */
    private boolean fun1(int[] nums) {
        for (int i = 0; i < nums.length - 3; i++) {
            if (nums[i] < nums[i + 2] && nums[i + 2] < nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 参考思路： https://leetcode-cn.com/problems/132-pattern/solution/132mo-shi-by-leetcode-2/
     */
    private boolean fun2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] > min[j]) {
                while (!stack.isEmpty() && stack.peek() <= min[j]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }
                stack.push(nums[j]);
            }
        }
        return false;
    }
}
