package greedy_algorithm.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 435. 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * <p>
 * 注意:
 * <p>
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 * <p>
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * <p>
 * 输出: 1
 * <p>
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 * <p>
 * 输入: [ [1,2], [1,2], [1,2] ]
 * <p>
 * 输出: 2
 * <p>
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 * <p>
 * 输入: [ [1,2], [2,3] ]
 * <p>
 * 输出: 0
 * <p>
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 */
public class EraseOverlapIntervals_435 {
    @Test
    public void test1() {
        //int[][] arr = new int[][]{{1, 2}, {1, 2}, {1, 2}};
        //System.out.println(eraseOverlapIntervals(arr));
        int[][] arr2 = new int[][]{{-5, -1}, {-4, -3}, {-3, -2}, {1, 3}};
        System.out.println(eraseOverlapIntervals(arr2));
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        return fun1(intervals);
    }

    private int fun1(int[][] intervals) {
        List<int[]> tempList = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            tempList.add(intervals[i]);
        }
        tempList.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        int result = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = tempList.get(i);
            int[] pre = tempList.get(i - 1);
            if (cur[0] < pre[1]) {
                if (cur[1] > pre[1]) {
                    // 移除当前段
                    cur[0] = pre[0];
                    cur[1] = pre[1];
                }
                result++;
            }
        }
        return result;
    }
}
