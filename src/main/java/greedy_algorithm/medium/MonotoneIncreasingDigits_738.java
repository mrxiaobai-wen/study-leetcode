package greedy_algorithm.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 738. 单调递增的数字
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * <p>
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 * <p>
 * 示例 1:
 * <p>
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 * <p>
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 * <p>
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 */
public class MonotoneIncreasingDigits_738 {

    @Test
    public void test() {
        Assert.assertEquals(123399, monotoneIncreasingDigits(123417));
        Assert.assertEquals(12339, monotoneIncreasingDigits(12341));
        Assert.assertEquals(9, monotoneIncreasingDigits(10));
        Assert.assertEquals(1234, monotoneIncreasingDigits(1234));
        Assert.assertEquals(299, monotoneIncreasingDigits(332));
    }

    public int monotoneIncreasingDigits(int N) {
        //return fun(N);
        //return fun2(N);
        return fun3(N);
    }

    /**
     * 参考思路：
     * 我们会找到第一个悬崖 s[i-1]>s[i]。然后，当悬崖存在时，我们将减去适当的数字，然后移动 i。再把剩下的数字补上 9，最后完成扫描。
     * 我们可以证明我们的算法是正确的，因为我们每次遇到悬崖时，减少的数字必须至少减少 1。然后，对其余数字的最大的可能选择是全部为 9。
     */
    private int fun3(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        int i = 1;
        int len = arr.length;
        // 往后寻找悬崖
        while (i < len && arr[i - 1] <= arr[i]) i++;
        // 上面一个循环执行完后，i出于悬崖点后一个位置，所以如果下面这个循环要执行的话，初始一定满足arr[i - 1] > arr[i]
        // 悬崖点减一后，往前寻找悬崖点
        while (0 < i && i < len && arr[i - 1] > arr[i]) arr[--i]--;
        // 此时的i如果有效，那么刚好处于悬崖点上
        for (int j = i + 1; j < len; j++) {
            // 悬崖后面统一填充9
            arr[j] = '9';
        }
        return Integer.parseInt(String.valueOf(arr));
    }

    /**
     * 贪心算法
     */
    private int fun2(int num) {
        String str = String.valueOf(num);
        String result = "";
        int len = str.length();
        search:
        for (int i = 0; i < len; i++) {
            for (char d = '1'; d <= '9'; d++) {
                String temp = result + repeat(d, len - i);
                if (str.compareTo(temp) < 0) {
                    result += (char) (d - 1);
                    continue search;
                }
            }
            result += '9';
        }
        return Integer.parseInt(result);
    }

    private String repeat(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 暴力搜索   -- 执行超时
     *
     * @param num
     * @return
     */
    private int fun(int num) {
        for (int i = num; i >= 1; i--) {
            int pre = Integer.MAX_VALUE;
            int temp = i;
            boolean flag = true;
            while (temp > 0) {
                int cur = temp % 10;
                temp /= 10;
                if (pre < cur) {
                    flag = false;
                    break;
                }
                pre = cur;
            }
            if (flag) {
                return i;
            }
        }
        return 0;
    }
}
