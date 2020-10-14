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

    /**
     * 荷兰国旗问题
     */
    public void sortColors(int[] nums) {
        //fun(nums);
        //fun2(nums);
        fun3(nums);
    }

    private void fun3(int[] nums) {
        int len = nums.length;
        int p0 = 0,p2 = len - 0;
        for (int i = 0;i <= p2;i++) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[p2];
                nums[p2] = nums[i];
                nums[i] = temp;
                p2--;
            }
            if (nums[i] == 0) {
                int temp = nums[p0];
                nums[p0] = nums[i];
                nums[i] = temp;
                p0++;
            }
        }
    }

    /**
     * 进阶：常量空间，一遍扫描，双指针
     * https://leetcode-cn.com/problems/sort-colors/solution/yan-se-fen-lei-by-leetcode-solution/
     */
    private void fun2(int[] nums) {
        int p0 = 0,p1 = 0;
        for (int i = 0;i < nums.length;i++) {
            if (nums[i] == 1) {
                int temp = nums[p1];
                nums[p1] = 1;
                nums[i] = temp;
                p1++;
            } else if (nums[i] == 0) {
                int temp = nums[p0];
                nums[p0] = nums[i];
                nums[i] = temp;
                if (p0 < p1) {
                    int temp1 = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp1;
                }
                p0++;
                p1++;
            }
        }
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
