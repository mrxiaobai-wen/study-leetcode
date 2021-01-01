package daily_question.daily_question_2020;

/**
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * <p>
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */
public class PowX_N_50 {

    public double myPow(double x, int n) {
        return fun(x, n);
    }

    private double fun(double x, int n) {
        return quickMul(x, n);
    }

    /**
     * 快速幂 + 递归
     * 推导地址：
     * https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
     */
    private double quickMul(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        if (n < 0) {
            return 1.0 / quickMul(x, -1 * n);
        }
        double tempResult = quickMul(x, n / 2);
        return n % 2 == 0 ? tempResult * tempResult : tempResult * tempResult * x;
    }
}
