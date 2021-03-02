package heap.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1054. 距离相等的条形码
 * <p>
 * 在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
 * <p>
 * 请你重新排列这些条形码，使其中两个相邻的条形码 不能 相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,1,1,2,2,2] 输出：[2,1,2,1,2,1] 示例 2：
 * <p>
 * 输入：[1,1,1,1,2,2,3,3] 输出：[1,3,1,3,2,1,2,1]
 * <p>
 * 提示：
 * <p>
 * 1 <= barcodes.length <= 10000 1 <= barcodes[i] <= 10000
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/distant-barcodes 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RearrangeBarcodes_1054 {

    public int[] rearrangeBarcodes(int[] barcodes) {
        return fun(barcodes);
    }

    private int[] fun(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int barcode : barcodes) {
            map.put(barcode, map.getOrDefault(barcode, 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] result = new int[barcodes.length];
        int i = 0;
        while (queue.size() >= 2) {
            int[] first = queue.poll();
            int[] second = queue.poll();
            result[i++] = first[0];
            first[1]--;
            if (first[1] > 0) {
                queue.offer(first);
            }
            result[i++] = second[0];
            second[1]--;
            if (second[1] > 0) {
                queue.offer(second);
            }
        }
        while (queue.size() > 0) {
            int[] cur = queue.poll();
            result[i++] = cur[0];
        }
        return result;
    }
}
