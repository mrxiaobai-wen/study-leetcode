package week_competition.single_week.week_213;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1642. 可以到达的最远建筑
 *
 * 给你一个整数数组 heights ，表示建筑物的高度。另有一些砖块 bricks 和梯子 ladders 。
 *
 * 你从建筑物 0 开始旅程，不断向后面的建筑物移动，期间可能会用到砖块或梯子。
 *
 * 当从建筑物 i 移动到建筑物 i+1（下标 从 0 开始 ）时：
 *
 * 如果当前建筑物的高度 大于或等于 下一建筑物的高度，则不需要梯子或砖块
 * 如果当前建筑的高度 小于 下一个建筑的高度，您可以使用 一架梯子 或 (h[i+1] - h[i]) 个砖块
 * 如果以最佳方式使用给定的梯子和砖块，返回你可以到达的最远建筑物的下标（下标 从 0 开始 ）。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
 * 输出：4
 * 解释：从建筑物 0 出发，你可以按此方案完成旅程：
 * - 不使用砖块或梯子到达建筑物 1 ，因为 4 >= 2
 * - 使用 5 个砖块到达建筑物 2 。你必须使用砖块或梯子，因为 2 < 7
 * - 不使用砖块或梯子到达建筑物 3 ，因为 7 >= 6
 * - 使用唯一的梯子到达建筑物 4 。你必须使用砖块或梯子，因为 6 < 9
 * 无法越过建筑物 4 ，因为没有更多砖块或梯子。
 * 示例 2：
 *
 * 输入：heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
 * 输出：7
 * 示例 3：
 *
 * 输入：heights = [14,3,19,3], bricks = 17, ladders = 0
 * 输出：3
 *  
 *
 * 提示：
 *
 * 1 <= heights.length <= 105
 * 1 <= heights[i] <= 106
 * 0 <= bricks <= 109
 * 0 <= ladders <= heights.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/furthest-building-you-can-reach
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FurthestBuilding_1642 {

    @Test
    public void test() {
        Assert.assertEquals(7, furthestBuilding(new int[]{4, 12, 2, 7, 3, 18, 20, 3, 19}, 10, 2));
        Assert.assertEquals(3, furthestBuilding(new int[]{14, 3, 19, 3}, 17, 0));
    }

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        fun(heights, bricks, ladders, 0);
        return max;
    }

    int max = 0;

    /**
     * 递归
     */
    private void fun(int[] heights, int bricks, int ladders, int curIndex) {
        if (curIndex >= heights.length - 1) {
            // 已经到最远了
            max = heights.length - 1;
            return;
        }
        if (max == heights.length - 1) {
            // 已经到最远
            return;
        }
        max = Math.max(max, curIndex);
        if (heights[curIndex] >= heights[curIndex + 1]) {
            // 不需要浪费资源，直接下一级
            fun(heights, bricks, ladders, curIndex + 1);
            return;
        }
        if (bricks <= 0 && ladders <= 0) {
            return;
        }
        int temp = heights[curIndex + 1] - heights[curIndex];
        if (ladders > 0) {
            // 尝试使用梯子
            fun(heights, bricks, ladders - 1, curIndex + 1);
        }
        if (bricks >= temp) {
            // 尝试使用砖块
            fun(heights, bricks - temp, ladders, curIndex + 1);
        }
    }
}
