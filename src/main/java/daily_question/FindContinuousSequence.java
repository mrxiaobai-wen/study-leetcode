package daily_question;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题57 - II. 和为s的连续正数序列
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * <p>
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 * <p>
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= target <= 10^5
 */
public class FindContinuousSequence {
    @Test
    public void test1() {
        findContinuousSequence(9);
    }

    /**
     * 重点：一定要是连续正数序列
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        // 参考代码：滑动窗口
        return fun1(target);
    }

    /**
     * 参考代码：
     * 滑动窗口
     *
     * @param target
     * @return
     */
    private int[][] fun1(int target) {
        int right = 1;
        int left = 1;
        int sum = 0;
        List<int[]> result = new ArrayList<>();
        while (left <= target / 2) {
            if (sum < target) {
                sum += right;
                right++;
            } else if (sum > target) {
                sum -= left;
                left++;
            } else {
                int[] temp = new int[right - left];
                for (int i = left; i < right; i++) {
                    temp[i - left] = i;
                }
                result.add(temp);
                sum -= left;
                left++;
            }
        }
        int[][] resultArr = new int[result.size()][];
        return result.toArray(resultArr);
    }
}
