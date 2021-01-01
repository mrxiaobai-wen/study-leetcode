package daily_question.daily_question_2020;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 面试题13. 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 1：
 * <p>
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 * <p>
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 */
public class MovingCount {
    @Test
    public void test() {
        System.out.println(movingCount(3, 2, 17));
        System.out.println(movingCount(2, 3, 1));
        System.out.println(movingCount(3, 1, 0));
    }

    public int movingCount(int m, int n, int k) {
        return fun(m, n, k);
    }

    private int fun(int m, int n, int k) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.add(0);
        set.add(0);
        int[] dx = new int[]{1, 1, 0};
        int[] dy = new int[]{0, 1, 1};
        count++;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                int temp = queue.poll();
                int x = temp / 1000;
                int y = temp % 1000;
                for (int i = 0; i < 3; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];
                    if (newX >= m || newY >= n || !calculate(newX, newY, k)) {
                        continue;
                    }
                    int num = newX * 1000 + newY;
                    if (!set.contains(num)) {
                        queue.add(num);
                        set.add(num);
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean calculate(int x, int y, int k) {
        int count = 0;
        while (x != 0) {
            count += x % 10;
            x /= 10;
        }
        while (y != 0) {
            count += y % 10;
            y /= 10;
        }
        return count <= k ? true : false;
    }
}
