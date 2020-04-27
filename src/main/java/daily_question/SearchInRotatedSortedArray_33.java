package daily_question;

import org.junit.Test;

/**
 * 33. 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class SearchInRotatedSortedArray_33 {
    @Test
    public void test() {
        System.out.println(search(new int[]{1, 3}, 1));
    }

    public int search(int[] nums, int target) {
        Integer result = fun(nums, 0, nums.length - 1, target);
        if (result == null) {
            return -1;
        } else {
            return result;
        }
    }

    private Integer fun(int[] nums, int begin, int end, int target) {
        if (begin > end) {
            return null;
        }
        if (begin == end) {
            if (target == nums[begin]) {
                return begin;
            } else {
                return null;
            }
        }
        int mid = (begin + end) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[begin] < nums[mid]) {
            // 前半段有序
            if (nums[begin] <= target && nums[mid] >= target) {
                return binarySearch(nums, begin, mid, target);
            } else {
                return fun(nums, mid + 1, end, target);
            }
        } else {
            // 后半段有序
            if (nums[mid + 1] <= target && nums[end] >= target) {
                return binarySearch(nums, mid + 1, end, target);
            } else {
                return fun(nums, begin, mid, target);
            }
        }
    }

    private Integer binarySearch(int[] nums, int begin, int end, int target) {
        if (begin > end) {
            return null;
        }
        int mid = (begin + end) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return binarySearch(nums, begin, mid - 1, target);
        } else {
            return binarySearch(nums, mid + 1, end, target);
        }
    }
}
