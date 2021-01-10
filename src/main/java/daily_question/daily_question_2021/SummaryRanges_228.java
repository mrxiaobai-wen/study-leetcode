package daily_question.daily_question_2021;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. 汇总区间
 * <p>
 * 给定一个无重复元素的有序整数数组 nums 。
 * <p>
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * "a->b" ，如果 a != b "a" ，如果 a == b
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,1,2,4,5,7] 输出：["0->2","4->5","7"] 解释：区间范围是： [0,2] --> "0->2" [4,5] --> "4->5" [7,7] --> "7" 示例 2：
 * <p>
 * 输入：nums = [0,2,3,4,6,8,9] 输出：["0","2->4","6","8->9"] 解释：区间范围是： [0,0] --> "0" [2,4] --> "2->4" [6,6] --> "6" [8,9] -->
 * "8->9" 示例 3：
 * <p>
 * 输入：nums = [] 输出：[] 示例 4：
 * <p>
 * 输入：nums = [-1] 输出：["-1"] 示例 5：
 * <p>
 * 输入：nums = [0] 输出：["0"]
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 20 -231 <= nums[i] <= 231 - 1 nums 中的所有值都 互不相同 nums 按升序排列
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/summary-ranges 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SummaryRanges_228 {

    public List<String> summaryRanges(int[] nums) {
        return fun(nums);
    }

    private List<String> fun(int[] nums) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nums.length; ) {
            int start = nums[i];
            int j = i + 1;
            for (; j < nums.length; j++) {
                if (nums[j] != nums[j - 1] + 1) {
                    break;
                }
            }
            if (i == j - 1) {
                result.add(nums[i] + "");
                i++;
            } else {
                result.add(nums[i] + "->" + nums[j - 1]);
                i = j;
            }
        }
        return result;
    }
}
