package two_pointers.simple;

import org.junit.Test;

import java.util.Arrays;

/**
 * 283. 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MoveZeroes_283 {

    @Test
    public void test() {
        int[] arr = new int[]{0,1,0,3,12};
        moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void moveZeroes(int[] nums) {
        fun(nums);
    }

    private void fun(int[] nums) {
        int len = nums.length;
        for (int i = len - 1;i > 0;i--) {
            for (int j = 0;j < i;j++) {
                if (nums[j] == 0) {
                    int temp = nums[j + 1];
                    nums[j + 1] = 0;
                    nums[j] = temp;
                }
            }
        }
    }
}
