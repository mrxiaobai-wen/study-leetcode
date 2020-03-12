package greedy_algorithm.medium;

import java.util.*;

/**
 * 1338. 数组大小减半
 * 给你一个整数数组 arr。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。
 * <p>
 * 返回 至少 能删除数组中的一半整数的整数集合的最小大小。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,3,3,3,5,5,5,2,2,7]
 * 输出：2
 * 解释：选择 {3,7} 使得结果数组为 [5,5,5,2,2]、长度为 5（原数组长度的一半）。
 * 大小为 2 的可行集合有 {3,5},{3,2},{5,2}。
 * 选择 {2,7} 是不可行的，它的结果数组为 [3,3,3,3,5,5,5]，新数组长度大于原数组的二分之一。
 * 示例 2：
 * <p>
 * 输入：arr = [7,7,7,7,7,7]
 * 输出：1
 * 解释：我们只能选择集合 {7}，结果数组为空。
 * 示例 3：
 * <p>
 * 输入：arr = [1,9]
 * 输出：1
 * 示例 4：
 * <p>
 * 输入：arr = [1000,1000,3,7]
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：arr = [1,2,3,4,5,6,7,8,9,10]
 * 输出：5
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5
 * arr.length 为偶数
 * 1 <= arr[i] <= 10^5
 */
public class MinSetSize_1338 {
    public int minSetSize(int[] arr) {
        return fun(arr);
    }

    private int fun(int[] arr) {
        int len = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int temp : arr) {
            if (!map.containsKey(temp)) {
                map.put(temp, 0);
            }
            map.put(temp, map.get(temp) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        list.addAll(map.entrySet());
        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        int sum = 0;
        int result = 0;
        for (int i = 0; i < list.size() && sum < len / 2; i++) {
            Map.Entry<Integer, Integer> entry = list.get(i);
            sum += entry.getValue();
            result++;
        }
        return result;
    }
}
