package daily_question.daily_question_2020;

import java.util.*;

/**
 * 365. 水壶问题
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * <p>
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * <p>
 * 你允许：
 * <p>
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1: (From the famous "Die Hard" example)
 * <p>
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 */
public class WaterAndJugProblem_365 {
    public boolean canMeasureWater(int x, int y, int z) {
        return fun(x, y, z);
    }

    /**
     * 参考代码
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    private boolean fun(int x, int y, int z) {
        Stack<int[]> stack = new Stack<>();  // 存储所有可能的状态
        Set<Map.Entry<Integer, Integer>> seenSet = new HashSet<>();  // 存储处理过的状态
        stack.push(new int[]{0, 0});
        while (!stack.isEmpty()) {
            int[] arr = stack.pop();
            if ((arr[0] == z) || (arr[1] == z) || (arr[0] + arr[1] == z)) return true;
            Map.Entry<Integer, Integer> currEntry = new AbstractMap.SimpleEntry<>(arr[0], arr[1]);
            if (seenSet.contains(currEntry)) continue;  // 之前处理过这个状态，跳过
            seenSet.add(currEntry);  // 把处理过的这个状态加入到已经处理过的集合内
            stack.add(new int[]{0, arr[1]});  // 倒空x
            stack.add(new int[]{arr[0], 0});  // 倒空y
            stack.add(new int[]{x, arr[1]});  // 加满x
            stack.add(new int[]{arr[0], y});   // 加满y
            // x往y中倒水
            if (arr[0] + arr[1] < y) stack.add(new int[]{0, arr[0] + arr[1]});  // 如果不会倒满，x空，y是当前两桶水之和
            else stack.add(new int[]{arr[0] + arr[1] - y, y});  // 如果会倒满，x中会有剩余，y满
            // y往x中倒水
            if (arr[0] + arr[1] < x) stack.add(new int[]{arr[0] + arr[1], 0});  // 如果不会倒满，y空，x是当前两桶水之和
            else stack.add(new int[]{x, arr[0] + arr[1] - x});  // 如果会倒满，y中会有剩余，x满
        }
        return false;  // 没有达到z，桟已空，返回false不可能
    }
}
