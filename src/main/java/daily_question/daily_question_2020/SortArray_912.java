package daily_question.daily_question_2020;

/**
 * 912. 排序数组
 * 给你一个整数数组 nums，将该数组升序排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 50000
 * -50000 <= nums[i] <= 50000
 */
public class SortArray_912 {
    public int[] sortArray(int[] nums) {
        // todo 下面各个排序方式各写一遍
        // 冒泡 执行超时
        // return bubbleSort(nums);
        // todo 选择
        return selectSort(nums);
        // todo 插入
        // todo 堆
        // todo 归并
        // todo 快速
        // todo 希尔
        // todo 计数
        // todo 桶
        // todo 基数
    }

    /**
     * 冒泡排序
     */
    private int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean endFlag = true;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    endFlag = false;
                }
            }
            if (endFlag) {
                break;
            }
        }
        return nums;
    }

    /**
     * 选择排序
     */
    private int[] selectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }
        return nums;
    }
}
