package greedy_algorithm.medium;

import org.junit.Test;

import java.util.*;

/**
 * 881. 救生艇
 * 第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
 * <p>
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 * <p>
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：people = [1,2], limit = 3
 * 输出：1
 * 解释：1 艘船载 (1, 2)
 * 示例 2：
 * <p>
 * 输入：people = [3,2,2,1], limit = 3
 * 输出：3
 * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
 * 示例 3：
 * <p>
 * 输入：people = [3,5,3,4], limit = 5
 * 输出：4
 * 解释：4 艘船分别载 (3), (3), (4), (5)
 * 提示：
 * <p>
 * 1 <= people.length <= 50000
 * 1 <= people[i] <= limit <= 30000
 */
public class BoatsToSavePeople_881 {

    @Test
    public void test() {
        System.out.println(numRescueBoats(new int[]{3, 1, 7}, 7));
    }

    public int numRescueBoats(int[] people, int limit) {
        return fun(people, limit);
    }

    /**
     * 尽量一个大个头和一个小个头安排在一起
     * </p>
     * 如：[5,1,4,2]
     *
     * @param people
     * @param limit
     * @return
     */
    private int fun(int[] people, int limit) {
        Map<Integer, Integer> peopleMap = new HashMap<>();
        for (int temp : people) {
            if (!peopleMap.containsKey(temp)) {
                peopleMap.put(temp, 0);
            }
            peopleMap.put(temp, peopleMap.get(temp) + 1);
        }
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>();
        entryList.addAll(peopleMap.entrySet());
        entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return -1 * (o1.getKey() - o2.getKey());
            }
        });
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : entryList) {
            int key = entry.getKey();
            if (!peopleMap.containsKey(key) || peopleMap.get(key) <= 0) {
                continue;
            }
            if (key >= limit) {
                result += peopleMap.get(key);
                peopleMap.remove(key);
                continue;
            }
            int value = peopleMap.get(key);
            if (2 * key <= limit) {
                result += value / 2;
                value = value % 2;
            }
            for (int tempKey = limit - key; value > 0 && tempKey >= 1; tempKey--) {
                if (!peopleMap.containsKey(tempKey)
                        || peopleMap.get(tempKey) <= 0
                        || tempKey == key) {
                    continue;
                }
                int tempValue = peopleMap.get(tempKey);
                if (tempValue >= value) {
                    peopleMap.put(tempKey, tempValue - value);
                    result += value;
                    value = 0;
                } else {
                    result += tempValue;
                    value = value - tempValue;
                    peopleMap.remove(tempKey);
                }
            }
            if (value > 0) {
                result += value;
            }
            peopleMap.remove(key);
        }
        return result;
    }
}
