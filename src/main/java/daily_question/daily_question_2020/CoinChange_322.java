package daily_question.daily_question_2020;

import org.junit.Test;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 */
public class CoinChange_322 {

    @Test
    public void test() {
        coinChange(new int[]{2}, 3);
    }

    public int coinChange(int[] coins, int amount) {
        // 这道题贪心算法是行不通的！！！
        //return fun1(coins,amount);

        // 动态规划，自顶向下
        //return fun2(coins, amount, new int[amount]);

        // 动态规划，自下向上
        return fun3(coins, amount);
    }

    /**
     * 使用贪心思想：
     * 要求使用最少数量的硬币来找零，那么就优先使用面值大的硬币
     *
     * @param coins
     * @param amount
     * @return
     */
    private int fun1(int[] coins, int amount) {
        if (amount == 0) {
            // 不需要找零
            return 0;
        }
        Arrays.sort(coins);
        int i = coins.length - 1;
        int result = 0;
        while (amount > 0 && i >= 0) {
            if (amount < coins[i]) {
                i--;
                continue;
            }
            int num = amount / coins[i];
            if (num > 0) {
                result += num;
                amount -= coins[i] * num;
            }
            i--;
        }
        if (result > 0 && amount == 0) {
            return result;
        }
        return -1;
    }

    /**
     * 参考代码：
     * 思想：递归   --自顶向下
     *
     * @param coins
     * @param amount
     * @param count
     * @return
     */
    private int fun2(int[] coins, int amount, int[] count) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (count[amount - 1] != 0) {
            return count[amount - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = fun2(coins, amount - coin, count);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        count[amount - 1] = min == Integer.MAX_VALUE ? -1 : min;
        return count[amount - 1];
    }

    /**
     * 参考代码：
     * 思想：动态规划——自下而上
     *
     * @param coins
     * @param amout
     * @return
     */
    private int fun3(int[] coins, int amout) {
        int[] dp = new int[amout + 1];
        //Arrays.fill(dp,Integer.MAX_VALUE);
        Arrays.fill(dp, amout + 1);
        dp[0] = 0;
        for (int i = 1; i <= amout; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    // 上面如果填充最大值，那么下面在+1则会超出范围
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        //return dp[amout] == Integer.MAX_VALUE ? -1 : dp[amout];
        return dp[amout] > amout ? -1 : dp[amout];
    }
}
