package dynamic_programming.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 486. 预测赢家
 * <p>
 * 给定一个表示分数的非负整数数组。 玩家1从数组任意一端拿取一个分数，随后玩家2继续从剩余数组任意一端拿取分数，
 * 然后玩家1拿，……。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。
 * 最终获得分数总和最多的玩家获胜。
 * <p>
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1, 5, 2]
 * 输出: False
 * 解释: 一开始，玩家1可以从1和2中进行选择。
 * 如果他选择2（或者1），那么玩家2可以从1（或者2）和5中进行选择。如果玩家2选择了5，那么玩家1则只剩下1（或者2）可选。
 * 所以，玩家1的最终分数为 1 + 2 = 3，而玩家2为 5。
 * 因此，玩家1永远不会成为赢家，返回 False。
 * 示例 2:
 * <p>
 * 输入: [1, 5, 233, 7]
 * 输出: True
 * 解释: 玩家1一开始选择1。然后玩家2必须从5和7中进行选择。无论玩家2选择了哪个，玩家1都可以选择233。
 * 最终，玩家1（234分）比玩家2（12分）获得更多的分数，所以返回 True，表示玩家1可以成为赢家。
 * 注意:
 * <p>
 * 1 <= 给定的数组长度 <= 20.
 * 数组里所有分数都为非负数且不会大于10000000。
 * 如果最终两个玩家的分数相等，那么玩家1仍为赢家。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/predict-the-winner
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PredictTheWinner_486 {

    @Test
    public void test() {
        System.out.println(PredictTheWinner(new int[]{1, 5, 2}));
        System.out.println(PredictTheWinner(new int[]{1, 5, 233, 7}));
    }

    public boolean PredictTheWinner(int[] nums) {
        if (fun1(nums, 0, nums.length - 1, 1) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private int fun1(int[] nums, int start, int end, int flag) {
        if (start == end) {
            return nums[start] * flag;
        }
        int result1 = nums[start] * flag + fun1(nums, start + 1, end, -1 * flag);
        int result2 = nums[end] * flag + fun1(nums, start, end - 1, -1 * flag);
        // 这里之所以要乘flag
        // 1、如果flag=1,那么返回的就是先手走到当前步的最优结果；
        // 2、如果flag=-1，那么当前是后手，所以应该返回后手最差的一种情况，以使得上面先手有赢的可能；
        return flag * Math.max(flag * result1, flag * result2);
    }

    /**
     * todo 动态规划未理解
     */
    private boolean dp(int[] nums) {
        int[] dp = new int[nums.length];
        for (int s = nums.length; s >= 0; s--) {
            for (int e = s + 1; e < nums.length; e++) {
                int a = nums[s] - dp[e];
                int b = nums[e] - dp[e - 1];
                dp[e] = Math.max(a, b);
            }
        }
        return dp[nums.length - 1] >= 0;
    }
}
