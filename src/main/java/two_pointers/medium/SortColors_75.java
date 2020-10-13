package two_pointers.medium;

import org.junit.Test;

/**
 * 75. 颜色分类
 *
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 *
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 *
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortColors_75 {

    @Test
    public void test() {
        int[] nums = new int[]{2,0,2,1,1,0};
        fun(nums);
    }

    public void sortColors(int[] nums) {
        fun(nums);
    }

    /**
     * 进阶：常量空间，一遍扫描
     */
    private void fun2(int[] nums) {

    }

    private void fun(int[] nums) {
        // 时间复杂度为 n平方
        /*int len = nums.length;
        for (int i = len - 1;i > 0;i--) {
            for (int j = 0;j < i;j++) {
                if (nums[j] > nums[j+ 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }*/

        // 时间复杂度为 2n
        int len = nums.length;
        int zeroNum = 0,oneNum = 0,twoNum = 0;
        for (int i = 0;i < len;i++) {
            if (nums[i] == 0) {
                zeroNum++;
            } else if (nums[i] == 1) {
                oneNum++;
            } else {
                twoNum++;
            }
        }
        for (int i = 0;i < zeroNum;i++) {
            nums[i] = 0;
        }
        for (int i = zeroNum;i < zeroNum + oneNum;i++) {
            nums[i] = 1;
        }
        for (int i = zeroNum + oneNum;i < zeroNum + oneNum + twoNum;i++) {
            nums[i] = 2;
        }
    }
}
