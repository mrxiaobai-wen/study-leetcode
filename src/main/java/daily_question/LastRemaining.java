package daily_question;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 面试题62. 圆圈中最后剩下的数字
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 * <p>
 * 输入: n = 10, m = 17
 * 输出: 2
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 */
public class LastRemaining {
    @Test
    public void test() {
        System.out.println(lastRemaining(5, 3));
    }

    public int lastRemaining(int n, int m) {
        // return fun(n,m);

        return fun2(n, m);
    }

    private int fun2(int n, int m) {
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tempList.add(i);
        }
        int index = 0;
        while (tempList.size() > 1) {
            index = (index + m - 1) % tempList.size();
            tempList.remove(index);
            //index = (index + 1) % tempList.size();
            // 已经删除了一个位置，后面的数据迁移，所以不用加1
            index = index % tempList.size();
        }
        return tempList.get(0);
    }

    /**
     * 模拟
     * 执行超时
     */
    private int fun(int n, int m) {
        Queue<Integer> tempQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            tempQueue.add(i);
        }
        while (tempQueue.size() > 1) {
            for (int i = 0; i < m - 1; i++) {
                tempQueue.add(tempQueue.poll());
            }
            tempQueue.poll();
        }
        return tempQueue.poll();
    }
}
