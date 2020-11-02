package two_pointers.difficult;

import org.junit.Assert;
import org.junit.Test;

/**
 * 面试题 17.21. 直方图的水量
 * <p>
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/volume-of-histogram-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class VolumeOfHistogramLcci {

    @Test
    public void test() {
        Assert.assertEquals(2, trap(new int[]{2, 0, 2}));
        Assert.assertEquals(6, trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public int trap(int[] height) {
        //return fun(height);
        return fun2(height);
    }

    /**
     * 动态规划
     */
    private int fun2(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        int len = height.length;
        int[] rightMax = new int[len];
        rightMax[len - 1] = height[len - 1];
        for (int j = len - 2; j >= 0; j--) {
            rightMax[j] = Math.max(rightMax[j + 1], height[j]);
        }
        int leftMax = 0;
        int result = 0;
        for (int i = 1; i < height.length - 1; i++) {
            leftMax = Math.max(leftMax, height[i - 1]);
            int temp = Math.min(leftMax, rightMax[i]);
            if (temp > height[i]) {
                result += temp - height[i];
            }
        }
        return result;
    }

    /**
     * 双指针
     * 思路：每个柱子上方能够盛水的大小，等于左右两边最大值中的较小值减去当前柱的高度。
     */
    public int fun(int[] height) {
        if (height == null || height.length < 3) return 0;
        int left = 0, right = height.length - 1;
        int leftMax = height[left], rightMax = height[right];
        int result = 0;
        while (left < right) {
            if (leftMax < rightMax) {
                result += leftMax - height[left];
                // 将left++单独拿出来，那么就容易理解：leftMax和height[left]不一定是同步的，如果同步，那么当前这一段不可以盛水
                left++;
                // 更新左边的最大值
                leftMax = Math.max(leftMax, height[left]);
            } else {
                result += rightMax - height[right];
                right--;
                // 更新右边的最大值
                rightMax = Math.max(rightMax, height[right]);
            }
        }
        return result;
    }
}
