package greedy_algorithm.medium;

import org.junit.Test;

import java.util.*;

/**
 * 621. 任务调度器
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 * <p>
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * <p>
 * 你需要计算完成所有任务所需要的最短时间。
 * <p>
 * 示例 1：
 * <p>
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 * 注：
 * <p>
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 */
public class LeastInterval_621 {
    @Test
    public void test() {
        char[] chars = new char[]{'A', 'A', 'A', 'B', 'B', 'B'};
        leastInterval(chars, 2);
    }


    public int leastInterval(char[] tasks, int n) {
        return fun1(tasks, n);
    }

    private int fun1(char[] tasks, int n) {
        TreeMap<Character, Integer> taskMap = new TreeMap<>();
        for (char temp : tasks) {
            if (taskMap.containsKey(temp)) {
                taskMap.put(temp, taskMap.get(temp) + 1);
            } else {
                taskMap.put(temp, 1);
            }
        }
        int curTime = 0;
        // 保存每个任务在上一次执行是的时间节点
        int[] lastExecuteIndex = new int[26];
        while (true) {
            boolean endFlag = true;
            curTime++;
            // 注意点：我们应该优先安排执行次数多的任务！！！
            List<Map.Entry<Character, Integer>> taskList = new ArrayList<>();
            taskList.addAll(taskMap.entrySet());
            taskList.sort(new Comparator<Map.Entry<Character, Integer>>() {
                @Override
                public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                    // 按照降序排序
                    return -1 * (o1.getValue() - o2.getValue());
                }
            });
            for (Map.Entry<Character, Integer> entry : taskList) {
                endFlag = false;
                int lastIndex = entry.getKey() - 'A';
                if (curTime - lastExecuteIndex[lastIndex] > n || lastExecuteIndex[lastIndex] == 0) {
                    entry.setValue(entry.getValue() - 1);
                    lastExecuteIndex[lastIndex] = curTime;
                    if (entry.getValue() <= 0) {
                        taskMap.remove(entry.getKey());
                    }
                    break;
                }
            }
            if (endFlag) {
                // 完全没有执行任务了，上面的curTime多加了一次
                curTime--;
                break;
            }
        }
        return curTime;
    }
}
