package daily_question.daily_question_2021;

import java.util.ArrayList;
import java.util.List;

/**
 * 830. 较大分组的位置
 * <p>
 * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
 * <p>
 * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 * <p>
 * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
 * <p>
 * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
 * <p>
 * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abbxxxxzzy" 输出：[[3,6]] 解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。 示例 2：
 * <p>
 * 输入：s = "abc" 输出：[] 解释："a","b" 和 "c" 均不是符合要求的较大分组。 示例 3：
 * <p>
 * 输入：s = "abcdddeeeeaabbbcd" 输出：[[3,5],[6,9],[12,14]] 解释：较大分组为 "ddd", "eeee" 和 "bbb" 示例 4：
 * <p>
 * 输入：s = "aba" 输出：[]   提示：
 * <p>
 * 1 <= s.length <= 1000 s 仅含小写英文字母
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/positions-of-large-groups 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LargeGroupPositions_830 {

    public List<List<Integer>> largeGroupPositions(String s) {
        return fun(s);
    }

    private List<List<Integer>> fun(String s) {
        List<List<Integer>> result = new ArrayList<>();
        char[] arr = s.toCharArray();
        for (int start = 0; start < arr.length; start++) {
            int end = start;
            while (end < arr.length && arr[start] == arr[end]) {
                end++;
            }
            if (end - start >= 3) {
                List<Integer> tempList = new ArrayList<>();
                tempList.add(start);
                tempList.add(end - 1);
                result.add(tempList);
            }
            start = end - 1;
        }
        return result;
    }
}
