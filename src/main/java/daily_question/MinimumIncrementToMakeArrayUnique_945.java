package daily_question;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 945. 使数组唯一的最小增量
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * <p>
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 * <p>
 * 示例 1:
 * <p>
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 * 示例 2:
 * <p>
 * 输入：[3,2,1,2,1,7]
 * 输出：6
 * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 * 提示：
 * <p>
 * 0 <= A.length <= 40000
 * 0 <= A[i] < 40000
 */
public class MinimumIncrementToMakeArrayUnique_945 {
    @Test
    public void test() {
        minIncrementForUnique(new int[]{1, 2, 2});
    }

    public int minIncrementForUnique(int[] A) {
        return fun(A);
    }

    /**
     * 执行超时
     */
    private int fun(int[] arr) {
        Set<Integer> tempSet = new HashSet<>();
        Arrays.sort(arr);
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!tempSet.contains(arr[i])) {
                tempSet.add(arr[i]);
                continue;
            }
            /*
            将每个数往上加，知道tempSet中不包含他，这样做的一个前提：
            有A、B两个数，且A<=B<C,那么将A、B中的任何一个加到C所经过的与A加到B、B加到C所经过的长度一样；
             */
            arr[i]++;
            result++;
            while (tempSet.contains(arr[i])) {
                arr[i]++;
                result++;
            }
            tempSet.add(arr[i]);
        }
        return result;
    }

    private int fun2(int[] arr) {
        Set<Integer> tempSet = new HashSet<>();
        Arrays.sort(arr);
        int result = 0;
        int curMax = -1;
        for (int i = 0; i < arr.length; i++) {
            if (!tempSet.contains(arr[i])) {
                tempSet.add(arr[i]);
                continue;
            }
            if (arr[i] >= curMax) {
                curMax = arr[i];
            } else {
                // 避免重复计算，比如多个 1,1,1 的这种情况
                result += curMax - arr[i];
            }
            while (tempSet.contains(curMax)) {
                curMax++;
                result++;
            }
            tempSet.add(curMax);
        }
        return result;
    }
}
