package dynamic_programming.medium;

import org.junit.Test;

import java.util.*;

/**
 * 1386. 安排电影院座位
 * <p>
 * 如上图所示，电影院的观影厅中有 n 行座位，行编号从 1 到 n ，且每一行内总共有 10 个座位，列编号从 1 到 10 。
 * <p>
 * 给你数组 reservedSeats ，包含所有已经被预约了的座位。比如说，researvedSeats[i]=[3,8] ，它表示第 3 行第 8 个座位被预约了。
 * <p>
 * 请你返回 最多能安排多少个 4 人家庭 。4 人家庭要占据 同一行内连续 的 4 个座位。隔着过道的座位（比方说 [3,3] 和 [3,4]）不是连续的座位，
 * 但是如果你可以将 4 人家庭拆成过道两边各坐 2 人，这样子是允许的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 * 输出：4
 * 解释：上图所示是最优的安排方案，总共可以安排 4 个家庭。蓝色的叉表示被预约的座位，橙色的连续座位表示一个 4 人家庭。
 * 示例 2：
 * <p>
 * 输入：n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^9
 * 1 <= reservedSeats.length <= min(10*n, 10^4)
 * reservedSeats[i].length == 2
 * 1 <= reservedSeats[i][0] <= n
 * 1 <= reservedSeats[i][1] <= 10
 * 所有 reservedSeats[i] 都是互不相同的。
 */
public class MaxNumberOfFamilies_1386 {

    @Test
    public void test() {
        System.out.println(maxNumberOfFamilies(3, new int[][]{{1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}}));
    }

    /**
     * 可以使用记忆化搜索！
     * todo 使用位运算 https://leetcode-cn.com/problems/cinema-seat-allocation/solution/an-pai-dian-ying-yuan-zuo-wei-by-leetcode-solution/
     */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        return fun1(n, reservedSeats);
    }

    /**
     * 模拟分配
     * </p>
     * 超出内存限制
     */
    private int fun1(int n, int[][] reservedSeats) {
        int[][] seats = new int[n][10];
        for (int[] temp : reservedSeats) {
            int x = temp[0] - 1;
            int y = temp[1] - 1;
            seats[x][y] = 1;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if (seats[i][j] == 1) {
                    continue;
                }
                if (j + 3 >= 10) {
                    break;
                } else if (seats[i][j + 1] == 1) {
                    j++;
                } else if (seats[i][j + 2] == 1) {
                    j += 2;
                } else if (seats[i][j + 3] == 1) {
                    j += 3;
                } else if (check(j, j + 3)) {
                    j = j + 3;
                    result++;
                }

            }
        }
        return result;
    }

    private boolean check(int begin, int end) {
        if (begin == 2 || begin == 6
                || end == 3 || end == 7) {
            return false;
        }
        return true;
    }

    /**
     * 模拟分配-空间优化
     */
    private int fun2(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> reservedMap = new HashMap<>();
        for (int[] temp : reservedSeats) {
            if (!reservedMap.containsKey(temp[0])) {
                reservedMap.put(temp[0], new HashSet<>());
            }
            reservedMap.get(temp[0]).add(temp[1]);
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            Set<Integer> reservedList = reservedMap.get(i);
            if (reservedList == null || reservedList.size() == 0) {
                result += 2;
            } else if (reservedList.size() >= 7) {
                continue;
            } else {
                for (int j = 2; j <= 6; j++) {
                    if (reservedList.contains(j)) {
                        continue;
                    } else if (reservedList.contains(j + 1)) {
                        j += 1;
                    } else if (reservedList.contains(j + 2)) {
                        j += 2;
                    } else if (reservedList.contains(j + 3)) {
                        j += 3;
                    } else if (j != 3) {
                        result++;
                        j += 3;
                    }
                }
            }
        }
        return result;
    }
}
