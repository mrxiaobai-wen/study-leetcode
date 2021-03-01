package heap.medium;

import java.util.Arrays;

/**
 * 1753. 移除石子的最大得分
 * <p>
 * 你正在玩一个单人游戏，面前放置着大小分别为 a​​​​​​、b 和 c​​​​​​ 的 三堆 石子。
 * <p>
 * 每回合你都要从两个 不同的非空堆 中取出一颗石子，并在得分上加 1 分。当存在 两个或更多 的空堆时，游戏停止。
 * <p>
 * 给你三个整数 a 、b 和 c ，返回可以得到的 最大分数 。
 * <p>
 *   示例 1：
 * <p>
 * 输入：a = 2, b = 4, c = 6 输出：6 解释：石子起始状态是 (2, 4, 6) ，最优的一组操作是： - 从第一和第三堆取，石子状态现在是 (1, 4, 5) - 从第一和第三堆取，石子状态现在是 (0, 4, 4)
 * - 从第二和第三堆取，石子状态现在是 (0, 3, 3) - 从第二和第三堆取，石子状态现在是 (0, 2, 2) - 从第二和第三堆取，石子状态现在是 (0, 1, 1) - 从第二和第三堆取，石子状态现在是 (0, 0, 0)
 * 总分：6 分 。 示例 2：
 * <p>
 * 输入：a = 4, b = 4, c = 6 输出：7 解释：石子起始状态是 (4, 4, 6) ，最优的一组操作是： - 从第一和第二堆取，石子状态现在是 (3, 3, 6) - 从第一和第三堆取，石子状态现在是 (2, 3, 5)
 * - 从第一和第三堆取，石子状态现在是 (1, 3, 4) - 从第一和第三堆取，石子状态现在是 (0, 3, 3) - 从第二和第三堆取，石子状态现在是 (0, 2, 2) - 从第二和第三堆取，石子状态现在是 (0, 1, 1) -
 * 从第二和第三堆取，石子状态现在是 (0, 0, 0) 总分：7 分 。 示例 3：
 * <p>
 * 输入：a = 1, b = 8, c = 8 输出：8 解释：最优的一组操作是连续从第二和第三堆取 8 回合，直到将它们取空。 注意，由于第二和第三堆已经空了，游戏结束，不能继续从第一堆中取石子。  
 * <p>
 * 提示：
 * <p>
 * 1 <= a, b, c <= 105
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/maximum-score-from-removing-stones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximumScore_1753 {

    public int maximumScore(int a, int b, int c) {
        return fun(a, b, c);
    }

    /**
     * 贪心算法
     */
    private int fun(int a, int b, int c) {
        int num[] = new int[]{a, b, c};
        Arrays.sort(num);
        if (num[0] + num[1] < num[2]) {
            return num[0] + num[1];
        }
        return (a + b + c) / 2;
    }

    /**
     * 模拟
     */
    private int fun2(int a, int b, int c) {
        int num[] = new int[]{a, b, c};
        Arrays.sort(num);
        int result = 0;
        while (num[0] > 0 && num[0] + num[1] < num[2]) {
            result++;
            num[0]--;
            num[1]--;
        }
        while (num[2] > 0 && num[1] > 0) {
            result++;
            num[2]--;
            num[1]--;
        }
        while (num[2] > 0 && num[0] > 0) {
            result++;
            num[2]--;
            num[0]--;
        }
        return result;
    }
}
