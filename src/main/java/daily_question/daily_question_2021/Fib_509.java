package daily_question.daily_question_2021;

/**
 * 509. 斐波那契数
 * <p>
 * 项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1 F(n) = F(n - 1) + F(n - 2)，其中 n > 1 给你 n ，请计算 F(n) 。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/fibonacci-number 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Fib_509 {

    public int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        int pre = 0;
        int cur = 1;
        for (int i = 2; i <= n; i++) {
            int temp = cur + pre;
            pre = cur;
            cur = temp;
        }
        return cur;
    }
}
