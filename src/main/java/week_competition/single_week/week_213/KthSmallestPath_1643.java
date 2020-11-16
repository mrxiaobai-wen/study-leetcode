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
 * Bob 站在单元格 (0, 0) ，想要前往目的地 destination ：(row, column) 。他只能向 右 或向 下 走。
 * 你可以为 Bob 提供导航 指令 来帮助他到达目的地 destination 。
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

    public String kthSmallestPath(int[] destination, int k) {
        //return fun(destination, k);

        /*result = new ArrayList<>();
        fun2("",destination[0],destination[1],0,0);
        Collections.sort(result);
        if (k - 1 >= result.size()) {
            return null;
        } else {
            return result.get(k - 1);
        }*/

        return fun3(destination, k);
    }

    /**
     * todo 参考思路： 组合数 这题刚拿到手一看就是二维的回溯法，可是回溯需要遍历所有情况，导致超时严重。 因此这里采用组合数求解： 1："H"和"V"的个数分别为destination[1]和destination[0],记为h,v;
     * 2：以第一个字符是"H"开头的字符串共有C(h+v-1,h-1); #这里C(n,m)表示组合数 3：比较k和C(h+v-1,h-1)的大小： ------如果k大，则说明以"H"开头的所有答案中均小于k,因此第一个字符为"V",则剩余"V"的数量为v--;
     * ------如果k不大，则说明第一个字符就是"H",剩余"H"的数量为h--; 4：重复上述2,3过程，直到h==0||v==0;此时剩余的直接加上就行;
     * <p>
     * 作者：hello-angel 链接：https://leetcode-cn.com/problems/kth-smallest-instructions/solution/javazu-he-shu-qiu-jie-si-lu-fen-xiang-by-hello-ang/
     * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private String fun4(int[] destination, int k) {
        int rows = destination[0];
        int cols = destination[1];
        //字符"H"的数量
        int h = cols;
        //字符"V"的数量
        int v = rows;
        StringBuffer sb = new StringBuffer();
        //动态规划求解组合数量，乘法容易溢出
        int[][] dp = new int[h + v][h];
        dp[0][0] = 1;
        for (int i = 1; i < h + v; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= i && j < h; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        //依次求解各个位置的字符
        while (h > 0 && v > 0) {
            int low = dp[h + v - 1][h - 1];
            if (k > low) {
                sb.append("V");
                v--;
                k -= low; //更新k值
            } else {
                sb.append("H");
                h--;
            }
        }
        if (h == 0) {//如果"H"用完,则把剩余位置都是"V"
            for (int i = 0; i < v; i++) {
                sb.append("V");
            }
        } else {//如果"V"用完,则剩余位置都是"H"
            for (int i = 0; i < h; i++) {
                sb.append("H");
            }
        }
        return sb.toString();
    }

    /**
     * 超出内存限制
     */
    private String fun3(int[] destination, int k) {
        int row = destination[0];
        int col = destination[1];
        List<List<String>> aboveList = new ArrayList<>();
        List<String> firstRowList = new ArrayList<>();
        firstRowList.add("");
        aboveList.add(firstRowList);
        // 右 H ；下 V
        for (int i = 1; i <= col; i++) {
            List<String> tempList = new ArrayList<>();
            List<String> preList = aboveList.get(i - 1);
            for (String s : preList) {
                String str = s + "H";
                tempList.add(str);
            }
            aboveList.add(tempList);
        }
        for (int i = 1; i <= row; i++) {
            List<List<String>> curTemp = new ArrayList<>();
            for (int j = 0; j <= col; j++) {
                List<String> curList = new ArrayList<>();
                // 上面
                List<String> above = aboveList.get(j);
                for (String s : above) {
                    curList.add(s + "V");
                }
                // 左侧
                if (j != 0) {
                    // 左侧
                    List<String> leftList = curTemp.get(j - 1);
                    for (String s : leftList) {
                        curList.add(s + "H");
                    }
                }
                curTemp.add(curList);
            }
            aboveList = curTemp;
        }
        List<String> result = aboveList.get(aboveList.size() - 1);
        Collections.sort(result);
        if (k - 1 >= result.size()) {
            return "";
        } else {
            return result.get(k - 1);
        }
    }


    List<String> result;

    /**
     * 递归超时
     */
    private void fun2(String preStr, int dRow, int dCol, int curRow, int curCol) {
        if (curRow == dRow && curCol == dCol) {
            result.add(preStr);
            return;
        } else if (curRow > dRow || curCol > dCol) {
            return;
        }
        // 向右 H
        fun2(preStr + "H", dRow, dCol, curRow, curCol + 1);
        // 向下
        fun2(preStr + "V", dRow, dCol, curRow + 1, curCol);
    }

    /**
     * 内存超出限制
     */
    public String fun(int[] destination, int k) {
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
