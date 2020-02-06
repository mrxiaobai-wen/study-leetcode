package depth_first_search;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Rob2_213 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 2};
        Rob2_213 example = new Rob2_213();
        System.out.print(example.rob(nums));
    }

    public int rob(int[] nums) {
        /*
        思路：与 198 相比，这时需要标记每一家是否已经打劫过。同时当打劫到最后一家的时候，且同时打劫了第一家，这时需要作出选择，
        在最后一家和第一家之间作出选择。
         */
        //return fun1(nums);

        /*
        参考题解：
        远不需要像上面考虑的那么复杂。很明显，第一家和最后一家只能二选一，不能同时抢，所以这就是将第一家和最后一家分作两列而已，取
        其中最大值。
         */
        return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length));
    }

    private int rob(int[] nums, int start, int size) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int cur = 0;
        int pre = 0;
        for (int i = start; i < size; i++) {
            int temp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = temp;
        }
        return cur;
    }

    /**
     * 采用动态规划。
     * 使用负数标记已经被抢劫
     *
     * @param nums
     * @return
     */
    private int fun1(int[] nums) {
        /*
        特殊情况一：2,3,2。这样执行下去，preMax不好判断是否记录了第一家的金额。
         */
        int preMax = 0;
        int curMax = 0;
        int homes = nums.length;
        for (int i = 0; i < homes; i++) {
            int tempMax = curMax;
            if (preMax + nums[i] > curMax) {
                if (i == homes - 1) {
                    if (nums[0] >= 0) {
                        // 第一家没抢，那么这一家可以抢
                        curMax = preMax + nums[i];
                        if (i - 1 > 0 && nums[i - 1] < 0) {
                            // 被抢的恢复过来
                            nums[i - 1] *= -1;
                        }
                        nums[i] *= -1;
                    } else {
                        // 第一家抢了，这时要在第一家和最后一家做比较
                        if (Math.abs(nums[i]) > Math.abs(nums[0])) {
                            // 放弃第一家，抢最后一家
                            nums[0] *= -1;
                            curMax = preMax + nums[i];
                        }
                    }
                } else {
                    curMax = preMax + nums[i];
                    if (i - 1 >= 0 && nums[i - 1] < 0) {
                        // 被抢的恢复过来
                        nums[i - 1] *= -1;
                        //tempMax -= nums[i -1];
                    }
                    //preMax = tempMax;
                    nums[i] *= -1;
                }
            } else {
                // 当前这家不抢，保持之前抢的最大金额
            }
            preMax = tempMax;
        }
        return curMax;
    }

}
