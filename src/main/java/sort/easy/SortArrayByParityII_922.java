package sort.easy;

/**
 * 922. 按奇偶排序数组 II
 *
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 *
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 *
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 * 示例：
 *
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *
 * 提示：
 *
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortArrayByParityII_922 {

    public int[] sortArrayByParityII(int[] nums) {
        return fun(nums);
    }

    /**
     * 使用双指针
     */
    private int[] fun(int[] nums) {
        int index1 = 0,index2 = 1;
        int[] result = new int[nums.length];
        for (int i = 0;i < nums.length;i++) {
            if (nums[i] % 2 == 0) {
                result[index1] = nums[i];
                index1 += 2;
            } else {
                result[index2] = nums[i];
                index2 += 2;
            }
        }
        return result;
    }

    /**
     * 原地排序使用双指针
     * </p>
     * 这个思路有点想当然了，但是可以再优化一下！！！
     */
    private int[] fun2(int[] nums) {
        int lastIndex = -1;
        for (int i = 0;i < nums.length;i++) {
            if (nums[i] % 2 == i % 2) {
                continue;
            } else {
                if (lastIndex == -1) {
                    lastIndex = i;
                } else {
                    int temp = nums[lastIndex];
                    nums[lastIndex] = nums[i];
                    nums[i] = temp;
                    lastIndex = -1;
                }
            }
        }
        return nums;
    }
}
