package dynamic_programming.medium;

import org.junit.Test;

/**
 * 357. 计算各个位数不同的数字个数
 * <p>
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 * <p>
 * 示例:
 * <p>
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountNumbersWithUniqueDigits_357 {

    @Test
    public void test() {
        System.out.println(fun1(1));
        System.out.println(fun1(2));
        System.out.println(fun1(10));
        System.out.println(fun1(11));
    }

    public int countNumbersWithUniqueDigits(int n) {
        return fun1(n);
    }

    private int fun1(int n) {
        if (n == 0) {
            return 1;
        }
        int[] result = new int[n + 1];
        result[0] = 0;
        result[1] = 10;
        for (int i = 2; i <= n; i++) {
            result[i] = result[i - 1] + 9 * calculate(i - 1, 9);
        }
        return result[n];
    }

    private int calculate(int n, int nums) {
        if (n <= 0) {
            return 1;
        } else if (n == 1) {
            return nums;
        } else if (nums <= 0) {
            return 0;
        }
        return (nums) * calculate(n - 1, nums - 1);
    }
}
