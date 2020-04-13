package dynamic_programming.simple;

import org.junit.Test;

/**
 * 面试题 08.01. 三步问题
 * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，
 * 你需要对结果模1000000007。
 * <p>
 * 示例1:
 * <p>
 * 输入：n = 3
 * 输出：4
 * 说明: 有四种走法
 * 示例2:
 * <p>
 * 输入：n = 5
 * 输出：13
 * 提示:
 * <p>
 * n范围在[1, 1000000]之间
 */
public class ThreeStepsProblemLcci {
    @Test
    public void test() {
        System.out.println(waysToStep(5));
    }

    public int waysToStep(int n) {
        return fun(n);
    }

    private int fun(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        }
        // 定义状态
        // 使用int定义的话，下面的计算将会损失精度
        long[] dp = new long[n + 1];
        // 初始化状态
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] % 1000000007) + (dp[i - 2] % 1000000007) + (dp[i - 3] % 1000000007);
            dp[i] = dp[i] % 1000000007;
        }
        return (int) dp[n];
    }
}
