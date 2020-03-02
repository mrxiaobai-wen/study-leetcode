package greedy_algorithm.simple;

import java.util.*;

/**
 * 公司计划面试 2N 人。第 i 人飞往 A 市的费用为 costs[i][0]，飞往 B 市的费用为 costs[i][1]。
 * <p>
 * 返回将每个人都飞到某座城市的最低费用，要求每个城市都有 N 人抵达。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[[10,20],[30,200],[400,50],[30,20]]
 * 输出：110
 * 解释：
 * 第一个人去 A 市，费用为 10。
 * 第二个人去 A 市，费用为 30。
 * 第三个人去 B 市，费用为 50。
 * 第四个人去 B 市，费用为 20。
 * <p>
 * 最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= costs.length <= 100
 * costs.length 为偶数
 * 1 <= costs[i][0], costs[i][1] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-city-scheduling
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoCitySchedCost_1029 {
    public static void main(String[] args) {
        TwoCitySchedCost_1029 ceshi = new TwoCitySchedCost_1029();
        int[][] costs = new int[][]{{10, 20}, {30, 200}, {400, 50}, {30, 20}};
        System.out.println(ceshi.twoCitySchedCost(costs));
    }

    public int twoCitySchedCost(int[][] costs) {
        return fun1(costs);
    }

    /**
     * 思路：将每个人往返AB两地的费用只差列出来，按照从大到小的顺序排序，优先将费用差大的人安排
     * 到期费用小的城市，从而实现总费用最小。
     *
     * @param costs
     * @return
     */
    private int fun1(int[][] costs) {
        // key:costs下标  value:该下标对应的两个费用差值
        TreeMap<Integer, Integer> discrepancy = new TreeMap<>();
        for (int i = 0; i < costs.length; i++) {
            discrepancy.put(i, Math.abs(costs[i][0] - costs[i][1]));
        }

        // 将Map按照value来排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        list.addAll(discrepancy.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                // 按照降序排序
                return -1 * (o1.getValue().compareTo(o2.getValue()));
            }
        });

        int result = 0;
        // 前往A、B两地的人数
        int aCount = 0;
        int bCount = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            if (costs[entry.getKey()][0] <= costs[entry.getKey()][1]) {
                if (aCount < list.size() / 2) {
                    aCount++;
                    result += costs[entry.getKey()][0];
                } else {
                    bCount++;
                    result += costs[entry.getKey()][1];
                }
            } else {
                if (bCount < list.size() / 2) {
                    bCount++;
                    result += costs[entry.getKey()][1];
                } else {
                    aCount++;
                    result += costs[entry.getKey()][0];
                }
            }
        }
        return result;
    }
}
