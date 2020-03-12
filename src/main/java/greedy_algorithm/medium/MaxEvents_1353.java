package greedy_algorithm.medium;

import org.junit.Test;

import java.util.*;

/**
 * 1353. 最多可以参加的会议数目
 * <p>
 * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
 * <p>
 * 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。
 * <p>
 * 请你返回你可以参加的 最大 会议数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：events = [[1,2],[2,3],[3,4]]
 * 输出：3
 * 解释：你可以参加所有的三个会议。
 * 安排会议的一种方案如上图。
 * 第 1 天参加第一个会议。
 * 第 2 天参加第二个会议。
 * 第 3 天参加第三个会议。
 * 示例 2：
 * <p>
 * 输入：events= [[1,2],[2,3],[3,4],[1,2]]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
 * 输出：4
 * 示例 4：
 * <p>
 * 输入：events = [[1,100000]]
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
 * 输出：7
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= events.length <= 10^5
 * events[i].length == 2
 * 1 <= events[i][0] <= events[i][1] <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-events-that-can-be-attended
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxEvents_1353 {
    @Test
    public void test() {
        maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}});
    }

    public int maxEvents(int[][] events) {
        return fun(events);
    }

    /**
     * 思路：优先参加先结束的会议；在同一天结束的会议，我们按顺序优先参加先开始的会议
     *
     * @param events
     * @return
     */
    private int fun(int[][] events) {
        List<int[]> list = new ArrayList<>();
        int len = events.length;
        for (int i = 0; i < len; i++) {
            list.add(events[i]);
        }
        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            int[] temp = list.get(i);
            for (int j = temp[0]; j <= temp[1]; j++) {
                if (!set.contains(j)) {
                    set.add(j);
                    break;
                }
            }
        }
        return set.size();
    }
}
