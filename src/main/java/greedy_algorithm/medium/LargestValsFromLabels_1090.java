package greedy_algorithm.medium;

import org.junit.Test;

import java.util.*;

/**
 * 1090. 受标签影响的最大值
 * 我们有一个项的集合，其中第 i 项的值为 values[i]，标签为 labels[i]。
 * <p>
 * 我们从这些项中选出一个子集 S，这样一来：
 * <p>
 * |S| <= num_wanted
 * 对于任意的标签 L，子集 S 中标签为 L 的项的数目总满足 <= use_limit。
 * 返回子集 S 的最大可能的 和。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 * 输出：9
 * 解释：选出的子集是第一项，第三项和第五项。
 * 示例 2：
 * <p>
 * 输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
 * 输出：12
 * 解释：选出的子集是第一项，第二项和第三项。
 * 示例 3：
 * <p>
 * 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
 * 输出：16
 * 解释：选出的子集是第一项和第四项。
 * 示例 4：
 * <p>
 * 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
 * 输出：24
 * 解释：选出的子集是第一项，第二项和第四项。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= values.length == labels.length <= 20000
 * 0 <= values[i], labels[i] <= 20000
 * 1 <= num_wanted, use_limit <= values.length
 */
public class LargestValsFromLabels_1090 {

    @Test
    public void test() {
        largestValsFromLabels(new int[]{5, 4, 3, 2, 1}, new int[]{1, 1, 2, 2, 3}, 3, 1);
    }

    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        return fun1(values, labels, num_wanted, use_limit);
    }

    private int fun1(int[] values, int[] labels, int num_wanted, int use_limit) {
        List<int[]> tempList = new ArrayList<>();
        for (int i = 0; i < labels.length; i++) {
            // 值-标签
            int[] arr = new int[2];
            arr[0] = values[i];
            arr[1] = labels[i];
            tempList.add(arr);
        }
        tempList.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 从大到小排列
                return -1 * (o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
            }
        });
        // key:标签  value:选择次数
        Map<Integer, Integer> select = new HashMap<>();
        int sum = 0;
        int num = 0;
        for (int i = 0; i < tempList.size() && num < num_wanted; i++) {
            // 值-标签
            int[] arr = tempList.get(i);
            if (!select.containsKey(arr[1])) {
                select.put(arr[1], 0);
            }
            if (select.get(arr[1]) >= use_limit) {
                continue;
            }
            sum += arr[0];
            select.put(arr[1], select.get(arr[1]) + 1);
            num++;
        }
        return sum;
    }
}
