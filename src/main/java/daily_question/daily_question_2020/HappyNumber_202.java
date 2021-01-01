package daily_question.daily_question_2020;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
 * 也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * <p>
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class HappyNumber_202 {
    @Test
    public void test() {
        System.out.println(isHappy(19));
    }

    public boolean isHappy(int n) {
        return fun(n);
    }

    private boolean fun(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int temp = n;
            int sum = 0;
            while (temp != 0) {
                sum += Math.pow(temp % 10, 2);
                temp /= 10;
            }
            if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
            }
            n = sum;
        }
        return true;
    }
}
