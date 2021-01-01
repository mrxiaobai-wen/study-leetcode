package daily_question.daily_question_2020;

import java.util.*;

/**
 * 面试题59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 * <p>
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 */
public class MaxQueue {
    Queue<Integer> valQueue = null;
    TreeMap<Integer, Integer> maxMap;

    public MaxQueue() {
        valQueue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        maxMap = new TreeMap<>();
    }

    public int max_value() {
        if (maxMap.isEmpty()) {
            return -1;
        }
        Map.Entry<Integer, Integer> entry = maxMap.lastEntry();
        return entry.getKey();
    }

    public void push_back(int value) {
        valQueue.add(value);
        if (maxMap.containsKey(value)) {
            maxMap.put(value, maxMap.get(value) + 1);
        } else {
            maxMap.put(value, 1);
        }
    }

    public int pop_front() {
        if (valQueue.isEmpty()) {
            return -1;
        }
        int temp = valQueue.poll();
        if (maxMap.get(temp) > 1) {
            maxMap.put(temp, maxMap.get(temp) - 1);
        } else {
            maxMap.remove(temp);
        }
        return temp;
    }
}
