package week_competition.single_week.week_213;

import org.junit.Assert;
import org.junit.Test;

public class FurthestBuilding {

    @Test
    public void test() {
        Assert.assertEquals(7,furthestBuilding(new int[]{4,12,2,7,3,18,20,3,19},10,2));
        Assert.assertEquals(3,furthestBuilding(new int[]{14,3,19,3},17,0));
    }

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        fun(heights,bricks,ladders,0);
        return max;
    }

    int max = 0;

    /**
     * 递归
     */
    private void fun(int[] heights,int bricks,int ladders,int curIndex) {
        if (curIndex >= heights.length - 1) {
            // 已经到最远了
            max = heights.length - 1;
            return;
        }
        if (max == heights.length - 1) {
            // 已经到最远
            return;
        }
        max = Math.max(max,curIndex);
        if (heights[curIndex] >= heights[curIndex + 1]) {
            // 不需要浪费资源，直接下一级
            fun(heights,bricks,ladders,curIndex + 1);
            return;
        }
        if (bricks <= 0 && ladders <= 0) {
            return;
        }
        int temp = heights[curIndex + 1] - heights[curIndex];
        if (ladders > 0) {
            // 尝试使用梯子
            fun(heights,bricks,ladders - 1,curIndex + 1);
        }
        if (bricks >= temp) {
            // 尝试使用砖块
            fun(heights,bricks - temp,ladders,curIndex + 1);
        }
    }
}
