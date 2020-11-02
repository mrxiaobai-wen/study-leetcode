package dynamic_programming.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 464. 我能赢吗
 * <p>
 * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和达到 100 的玩家，即为胜者。
 * <p>
 * 如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
 * <p>
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
 * <p>
 * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和），
 * 判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？
 * <p>
 * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * maxChoosableInteger = 10
 * desiredTotal = 11
 * <p>
 * 输出：
 * false
 * <p>
 * 解释：
 * 无论第一个玩家选择哪个整数，他都会失败。
 * 第一个玩家可以选择从 1 到 10 的整数。
 * 如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
 * 第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
 * 同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/can-i-win
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanIWin_464 {

    @Test
    public void test() {
        //System.out.println(fun(10,11));
        System.out.println(fun(16, 225));
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        return fun(maxChoosableInteger, desiredTotal);
    }

    private boolean fun(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal) {
            /*if (desiredTotal % 2 == 0) {
                return false;
            } else {
                return true;
            }*/
            return false;
        }
        int[] state = new int[desiredTotal + 1];
        Map<String, Boolean> resultMap = new HashMap<>();
        return backtraceWithMemo(state, desiredTotal, resultMap);
    }

    private boolean backtraceWithMemo(int[] state, int desiredTotal, Map<String, Boolean> map) {
        String key = Arrays.toString(state);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        for (int i = 1; i < state.length; i++) {
            if (state[i] == 0) {
                // 当前数没有被使用
                state[i] = 1;
                if (desiredTotal - i <= 0 || !backtraceWithMemo(state, desiredTotal - i, map)) {
                    state[i] = 0;
                    map.put(key, true);
                    return true;
                }
                state[i] = 0;
            }
        }
        map.put(key, false);
        return false;
    }
}
