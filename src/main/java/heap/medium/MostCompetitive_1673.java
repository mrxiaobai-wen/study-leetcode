package heap.medium;

import java.util.Stack;

/**
 * 1673. 找出最具竞争力的子序列
 * <p>
 * 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
 * <p>
 * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
 * <p>
 * 在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。 例如，[1,3,4] 比 [1,3,5]
 * 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,5,2,6], k = 2 输出：[2,6] 解释：在所有可能的子序列集合 {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]} 中，[2,6] 最具竞争力。 示例 2：
 * <p>
 * 输入：nums = [2,4,3,3,5,4,9,6], k = 4 输出：[2,3,3,4]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105 0 <= nums[i] <= 109 1 <= k <= nums.length
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-the-most-competitive-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MostCompetitive_1673 {

    public int[] mostCompetitive(int[] nums, int k) {
        return fun(nums, k);
    }

    /**
     * 使用单调栈： 1.当前元素大于等于栈顶元素，入栈；否则，将栈中大于当前元素的数一直出栈，再将当前元素压栈，以此保证栈中最小字典序的顺序。 2.其中记录删除了多少元素，题目保留k个元素，即删除n-k个元素，若删除的数目达到上限，则保留栈中元素不再删除。
     * 3.遍历一遍后，若栈中元素数目大于k个（即n-k>0），将多余元素出栈。
     */
    private int[] fun(int[] nums, int k) {
        int n = nums.length;
        if (k >= n) {
            return nums;
        }
        int[] result = new int[k];
        // 记录多余的数
        int p = n - k;
        Stack<Integer> stack = new Stack<>();
        // 维持一个单调栈
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && p > 0 && nums[i] < stack.peek()) {
                stack.pop();
                p--;
            }
            stack.push(nums[i]);
        }
        // 删除占中多余k的数
        for (int i = 0; i < p; i++) {
            stack.pop();
        }
        int j = k - 1;
        while (!stack.isEmpty()) {
            result[j--] = stack.pop();
        }
        return result;
    }
}
