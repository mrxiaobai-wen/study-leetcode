package greedy_algorithm.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 955. 删列造序 II
 * 给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
 * <p>
 * 选取一个删除索引序列，对于 A 中的每个字符串，删除对应每个索引处的字符。
 * <p>
 * 比如，有 A = ["abcdef", "uvwxyz"]，删除索引序列 {0, 2, 3}，删除后 A 为["bef", "vyz"]。
 * <p>
 * 假设，我们选择了一组删除索引 D，那么在执行删除操作之后，最终得到的数组的元素是按 字典序（A[0] <= A[1] <= A[2] ... <= A[A.length - 1]）排列的，
 * 然后请你返回 D.length 的最小可能值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：["ca","bb","ac"]
 * 输出：1
 * 解释：
 * 删除第一列后，A = ["a", "b", "c"]。
 * 现在 A 中元素是按字典排列的 (即，A[0] <= A[1] <= A[2])。
 * 我们至少需要进行 1 次删除，因为最初 A 不是按字典序排列的，所以答案是 1。
 * 示例 2：
 * <p>
 * 输入：["xc","yb","za"]
 * 输出：0
 * 解释：
 * A 的列已经是按字典序排列了，所以我们不需要删除任何东西。
 * 注意 A 的行不需要按字典序排列。
 * 也就是说，A[0][0] <= A[0][1] <= ... 不一定成立。
 * 示例 3：
 * <p>
 * 输入：["zyx","wvu","tsr"]
 * 输出：3
 * 解释：
 * 我们必须删掉每一列。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 */
public class DeleteColumnsToMakeSorted_955 {
    @Test
    public void test() {
        System.out.println(minDeletionSize(new String[]{"xga", "xfb", "yfa"}));
    }

    public int minDeletionSize(String[] A) {
        return fun1(A);
    }

    /**
     * 未考虑前一列相同的情况、后一列针对前一列分段满足字典有序的情况！！！
     *
     * @param A
     * @return
     */
    private int fun1(String[] A) {
        // 记录每一行相对于自己的位置是否已经有序，1代表已经有序
        int[] sortResult = new int[A.length];
        int result = 0;
        int row = A.length, col = A[0].length();
        char[][] arr = new char[A.length][];
        for (int i = 0; i < A.length; i++) {
            arr[i] = A[i].toCharArray();
        }
        for (int j = 0; j < col; j++) {
            char pre = 0;
            Integer preIndex = -1;
            List<Integer> tempSort = new ArrayList<>();
            boolean deleteFlag = false;
            boolean endFalg = true;
            for (int i = 0; i < row; i++) {
                if (sortResult[i] == 1) {
                    continue;
                }
                endFalg = false;
                if (arr[i][j] > pre) {
                    tempSort.add(i);
                    pre = arr[i][j];
                    preIndex = i;
                } else if (arr[i][j] == pre) {
                    tempSort.remove(preIndex);
                    preIndex = i;
                } else {
                    deleteFlag = true;
                    break;
                }
            }
            if (endFalg) {
                break;
            }
            if (deleteFlag) {
                result++;
            } else {
                for (int i : tempSort) {
                    sortResult[i] = 1;
                }
            }
        }
        return result;
    }

    /**
     * 参考代码：贪心算法
     * 记录所有可行列
     * </P>
     * 想法
     * <p>
     * 针对该问题，我们考虑保留哪些列去获得最终的有序结果，而不是删除哪些列。
     * <p>
     * 如果第一列不是字典序排列的，我们就必须删除它。
     * <p>
     * 否则，我们需要讨论是否保留第一列。会出现以下两种情况：
     * <p>
     * 如果我们不保留第一列，则最后答案的行需要保证有序；
     * <p>
     * 如果我们保留了第一列，那么最终答案的行（除去第一列）只需要在第一个字母相同的情况下需要保证有序。
     * <p>
     * 这个描述很难理解，看下面的例子：
     * <p>
     * 假设我们有 A = ["axx", "ayy", "baa", "bbb", "bcc"]，当我们保留第一列之后，最终行变成 R = ["xx", "yy", "aa", "bb", "cc"]，对于这些行，并不要求所有有序（R[0] <= R[1] <= R[2] <= R[3] <= R[4]），只需要达到一个较弱的要求：对于第一个字母相同的行保证有序（R[0] <= R[1] 和 R[2] <= R[3] <= R[4]）。
     * <p>
     * 现在，我们只将结论应用到第一列，但实际上这个结论对每列都合适。如果我们不能取用第一列，就删除它。否则，我们就取用第一列，因为无论如何都可以使要求更简单。
     * <p>
     * 算法
     * <p>
     * 首先没有任意列保留，对于每一列：如果保留后结果保持有序，就保留这一列；否则删除它。
     *
     * @param A
     * @return
     */
    private int fun2(String[] A) {
        int row = A.length, col = A[0].length();
        String[] preStr = new String[A.length];
        int result = 0;
        for (int j = 0; j < col; j++) {
            String[] curStr = Arrays.copyOf(preStr, preStr.length);
            for (int i = 0; i < row; i++) {
                curStr[i] = curStr[i] + A[i].charAt(j);
            }
            if (isSort(curStr)) {
                preStr = curStr;
            } else {
                result++;
            }
        }
        return result;
    }

    private boolean isSort(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}
