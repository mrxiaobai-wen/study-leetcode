package heap.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1760. 袋子里最少数目的球
 * <p>
 * 给你一个整数数组 nums ，其中 nums[i] 表示第 i 个袋子里球的数目。同时给你一个整数 maxOperations 。
 * <p>
 * 你可以进行如下操作至多 maxOperations 次：
 * <p>
 * 选择任意一个袋子，并将袋子里的球分到 2 个新的袋子中，每个袋子里都有 正整数 个球。 比方说，一个袋子里有 5 个球，你可以把它们分到两个新袋子里，分别有 1 个和 4 个球，或者分别有 2 个和 3 个球。
 * 你的开销是单个袋子里球数目的 最大值 ，你想要 最小化 开销。
 * <p>
 * 请你返回进行上述操作后的最小开销。
 * <p>
 * 示例 1： 输入：nums = [9], maxOperations = 2 输出：3 解释： - 将装有 9 个球的袋子分成装有 6 个和 3 个球的袋子。[9] -> [6,3] 。 - 将装有 6 个球的袋子分成装有 3 个和
 * 3 个球的袋子。[6,3] -> [3,3,3] 。 装有最多球的袋子里装有 3 个球，所以开销为 3 并返回 3 。
 * <p>
 * 示例 2： 输入：nums = [2,4,8,2], maxOperations = 4 输出：2 解释： - 将装有 8 个球的袋子分成装有 4 个和 4 个球的袋子。[2,4,8,2] -> [2,4,4,4,2] 。 - 将装有
 * 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,4,4,4,2] -> [2,2,2,4,4,2] 。 - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,4,4,2] ->
 * [2,2,2,2,2,4,2] 。 - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2] 。 装有最多球的袋子里装有 2 个球，所以开销为 2 并返回
 * 2 。
 * <p>
 * 示例 3： 输入：nums = [7,17], maxOperations = 2 输出：7
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105 1 <= maxOperations, nums[i] <= 109
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/minimum-limit-of-balls-in-a-bag
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumSize_1760 {

    public int minimumSize(int[] nums, int maxOperations) {
        // return fun1(nums,maxOperations);
        return fun2(nums, maxOperations);
    }

    /**
     * 解法一：平分最大值
     * </p>
     * 未考虑特殊情况，比如9拆分两次得到四，但是不平分的话可以得到3个3
     */
    private int fun1(int[] nums, int maxOperations) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
        }
        for (int i = 0; i < maxOperations; i++) {
            if (queue.size() >= 1) {
                int cur = queue.poll();
                queue.offer(cur / 2);
                queue.offer((cur + 1) / 2);
            }
        }
        return queue.peek();
    }

    /**
     * 思路：设置一个值为y,那么将nums的每一个数x都拆分成不大于y的部分需要的次数为(x - 1) / y向下取整。 然后计算总的需要的次数 z,二分寻找这个满足条件的最小z
     */
    private int fun2(int[] nums, int maxOperations) {
        // right可以去nums中的最大值
        long left = 1, right = 1000000000;
        long result = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (check(nums, mid, maxOperations)) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int) result;
    }

    private boolean check(int[] nums, long cost, int maxOperation) {
        long ans = 0;
        for (int cur : nums) {
            ans += (cur - 1) / cost;
        }
        return ans <= maxOperation;
    }
}
