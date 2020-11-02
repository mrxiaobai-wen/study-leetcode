package week_competition.single_week.week_213;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 1643. 第 K 条最小指令
 *
 * Bob 站在单元格 (0, 0) ，想要前往目的地 destination ：(row, column) 。他只能向 右 或向 下 走。你可以为 Bob 提供导航 指令 来帮助他到达目的地 destination 。
 *
 * 指令 用字符串表示，其中每个字符：
 *
 * 'H' ，意味着水平向右移动
 * 'V' ，意味着竖直向下移动
 * 能够为 Bob 导航到目的地 destination 的指令可以有多种，例如，如果目的地 destination 是 (2, 3)，"HHHVV" 和 "HVHVH" 都是有效 指令 。
 *
 * 然而，Bob 很挑剔。因为他的幸运数字是 k，他想要遵循 按字典序排列后的第 k 条最小指令 的导航前往目的地 destination 。k  的编号 从 1 开始 。
 *
 * 给你一个整数数组 destination 和一个整数 k ，请你返回可以为 Bob 提供前往目的地 destination 导航的 按字典序排列后的第 k 条最小指令 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：destination = [2,3], k = 1
 * 输出："HHHVV"
 * 解释：能前往 (2, 3) 的所有导航指令 按字典序排列后 如下所示：
 * ["HHHVV", "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH", "VHVHH", "VVHHH"].
 * 示例 2：
 *
 *
 *
 * 输入：destination = [2,3], k = 2
 * 输出："HHVHV"
 * 示例 3：
 *
 *
 *
 * 输入：destination = [2,3], k = 3
 * 输出："HHVVH"
 *  
 *
 * 提示：
 *
 * destination.length == 2
 * 1 <= row, column <= 15
 * 1 <= k <= nCr(row + column, row)，其中 nCr(a, b) 表示组合数，即从 a 个物品中选 b 个物品的不同方案数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-instructions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthSmallestPath_1643 {

    @Test
    public void test() {
        Assert.assertEquals("HHHVV", kthSmallestPath(new int[]{2, 3}, 1));
        Assert.assertEquals("HHVHV", kthSmallestPath(new int[]{2, 3}, 2));
        Assert.assertEquals("HHVVH", kthSmallestPath(new int[]{2, 3}, 3));
    }

    /**
     * 内存超出限制
     */
    public String kthSmallestPath(int[] destination, int k) {
        List<String>[][] arr = new List[destination[0] + 1][destination[1] + 1];
        List<String> temp = new ArrayList<>();
        temp.add("");
        arr[0][0] = temp;
        for (int row = 0; row <= destination[0]; row++) {
            for (int col = 0; col <= destination[1]; col++) {
                if (row == 0 && col == 0) {
                    continue;
                }
                List<String> curList = new ArrayList<>();
                // 从左来
                if (col - 1 >= 0) {
                    List<String> leftList = arr[row][col - 1];
                    for (String left : leftList) {
                        curList.add(left + "H");
                    }
                }
                if (row - 1 >= 0) {
                    List<String> upList = arr[row - 1][col];
                    for (String up : upList) {
                        curList.add(up + "V");
                    }
                }
                arr[row][col] = curList;
            }
        }
        List<String> list = arr[destination[0]][destination[1]];
        Collections.sort(list);
        if (k - 1 >= list.size()) {
            return "";
        }
        return list.get(k - 1);
    }
}
