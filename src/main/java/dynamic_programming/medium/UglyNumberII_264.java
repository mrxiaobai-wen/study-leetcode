package dynamic_programming.medium;

import java.util.*;

/**
 * 264. 丑数 II
 * 编写一个程序，找出第 n 个丑数。
 * <p>
 * 丑数就是质因数只包含 2, 3, 5 的正整数。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 * <p>
 * 1 是丑数。
 * n 不超过1690。
 */
public class UglyNumberII_264 {

    public int nthUglyNumber(int n) {
        // 预计算
        //return fun1(n);

        // 动态规划
        return fun2(n);
    }

    /**
     * 预计算
     */
    private int fun1(int n) {
        return ugly1.result[n - 1];
    }

    private static Ugly1 ugly1 = new Ugly1();

    static class Ugly1 {
        public static int[] result = new int[1690];

        Ugly1() {
            Set<Long> set = new HashSet<>();
            PriorityQueue<Long> queue = new PriorityQueue<>();
            queue.add(1L);
            set.add(1L);
            int[] temp = new int[]{2, 3, 5};
            long curUgly;
            long newUgly;
            for (int i = 0; i < 1690; i++) {
                curUgly = queue.poll();
                result[i] = (int) curUgly;
                for (int j = 0; j < 3; j++) {
                    newUgly = curUgly * temp[j];
                    if (!set.contains(newUgly)) {
                        set.add(newUgly);
                        queue.add(newUgly);
                    }
                }
            }
        }
    }

    /**
     * 动态规划
     */
    private int fun2(int n) {
        return ugly2.result[n - 1];
    }

    private static Ugly2 ugly2 = new Ugly2();

    static class Ugly2 {
        public static int[] result = new int[1690];

        Ugly2() {
            result[0] = 1;
            int i1 = 0;
            int i2 = 0;
            int i3 = 0;
            for (int i = 1; i < 1690; i++) {
                int ugly = Math.min(Math.min(result[i1] * 2, result[i2] * 3), result[i3] * 5);
                result[i] = ugly;
                if (result[i1] * 2 == ugly) i1++;
                if (result[i2] * 3 == ugly) i2++;
                if (result[i3] * 5 == ugly) i3++;
            }
        }
    }
}
