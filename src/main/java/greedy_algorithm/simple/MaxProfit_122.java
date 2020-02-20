package greedy_algorithm.simple;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProfit_122 {
    /**
     * 我们必须确定通过交易能够获得的最大利润（对于交易次数没有限制）。为此，我们需要找出那些共同使得利润最大化的买入及卖出价格。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        return fun1(prices);
    }

    /**
     * 思路：将每一笔能够获得的收益都拿到。
     *
     * @param prices
     */
    private int fun1(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                sum += prices[i] - prices[i - 1];
            }
        }
        return sum;
    }

    /**
     * 参考：
     * 有趣峰谷法！！！
     * fun1的复杂法
     *
     * @param prices
     * @return
     */
    private int fun2(int[] prices) {
        int maxProfit = 0;
        int peak = 0;
        int valley = 0;
        int i = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            maxProfit += peak - valley;
        }
        return maxProfit;
    }

    /**
     * 难以想到：
     * 暴力搜索法
     *
     * @param prices
     * @return
     */
    private int calculate(int[] prices, int s) {
        if (s >= prices.length) {
            return 0;
        }
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxProfit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > maxProfit) {
                        maxProfit = profit;
                    }
                }
            }
            if (maxProfit > max) {
                max = maxProfit;
            }
        }
        return max;
    }
}
