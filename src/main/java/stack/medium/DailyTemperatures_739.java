package stack.medium;

import org.junit.Test;

import java.util.Stack;

/**
 * 739. 每日温度
 * <p>
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/daily-temperatures 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DailyTemperatures_739 {

    @Test
    public void test() {
        int[] result1 = dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
    }

    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        result[result.length - 1] = 0;
        Stack<Obj> stack = new Stack<>();
        stack.add(new Obj(T.length - 1, T[T.length - 1]));
        for (int i = T.length - 2; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().value <= T[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = stack.peek().index - i;
            }
            stack.add(new Obj(i, T[i]));
        }
        return result;
    }

    class Obj {

        Obj(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public Integer index;
        public Integer value;
    }
}
