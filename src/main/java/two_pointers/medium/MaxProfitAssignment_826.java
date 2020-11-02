package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 826. 安排工作以达到最大收益
 * <p>
 * 有一些工作：difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。
 * <p>
 * 现在我们有一些工人。worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
 * <p>
 * 每一个工人都最多只能安排一个工作，但是一个工作可以完成多次。
 * <p>
 * 举个例子，如果 3 个工人都尝试完成一份报酬为 1 的同样工作，那么总收益为 $3。如果一个工人不能完成任何工作，他的收益为 $0 。
 * <p>
 * 我们能得到的最大收益是多少？
 * <p>
 * 示例：
 * <p>
 * 输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * 输出: 100
 * 解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
 * <p>
 * 提示:
 * <p>
 * 1 <= difficulty.length = profit.length <= 10000
 * 1 <= worker.length <= 10000
 * difficulty[i], profit[i], worker[i]  的范围是 [1, 10^5]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/most-profit-assigning-work
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProfitAssignment_826 {

    @Test
    public void test() {
        Assert.assertEquals(324, maxProfitAssignment(new int[]{68, 35, 52, 47, 86}, new int[]{67, 17, 1, 81, 3}, new int[]{92, 10, 85, 84, 82}));
        Assert.assertEquals(100, maxProfitAssignment(new int[]{2, 4, 6, 8, 10}, new int[]{10, 20, 30, 40, 50}, new int[]{4, 5, 6, 7}));

    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        return fun(difficulty, profit, worker);
    }

    private int fun(int[] difficulty, int[] profit, int[] worker) {
        int result = 0;
        for (int i = 0; i < worker.length; i++) {
            int ability = worker[i];
            int index = -1;
            for (int j = 0; j < difficulty.length; j++) {
                if (difficulty[j] <= ability) {
                    if (index == -1) {
                        index = j;
                    } else if (profit[j] > profit[index]) {
                        index = j;
                    }
                }
            }
            if (index != -1) {
                result += profit[index];
            }
        }
        return result;
    }
}
