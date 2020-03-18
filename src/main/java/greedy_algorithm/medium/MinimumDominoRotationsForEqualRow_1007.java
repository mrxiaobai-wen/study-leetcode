package greedy_algorithm.medium;

/**
 * 1007. 行相等的最少多米诺旋转
 * 在一排多米诺骨牌中，A[i] 和 B[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。（一个多米诺是两个从 1 到 6 的数字同列平铺形成的 —— 该平铺的每一半上都有一个数字。）
 * <p>
 * 我们可以旋转第 i 张多米诺，使得 A[i] 和 B[i] 的值交换。
 * <p>
 * 返回能使 A 中所有值或者 B 中所有值都相同的最小旋转次数。
 * <p>
 * 如果无法做到，返回 -1.
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * 输出：2
 * 解释：
 * 图一表示：在我们旋转之前， A 和 B 给出的多米诺牌。
 * 如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。
 * 示例 2：
 * <p>
 * 输入：A = [3,5,1,2,3], B = [3,6,3,3,4]
 * 输出：-1
 * 解释：
 * 在这种情况下，不可能旋转多米诺牌使一行的值相等。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A[i], B[i] <= 6
 * 2 <= A.length == B.length <= 20000
 */
public class MinimumDominoRotationsForEqualRow_1007 {
    public int minDominoRotations(int[] A, int[] B) {
        return fun(A, B);
    }

    /**
     * 参考代码：
     * </p>
     * 思路：如果能够翻转成功，那么可以肯定的是这个相同的数一定是A[0]或者是B[0]中的一个。
     *
     * @param A
     * @param B
     * @return
     */
    private int fun(int[] A, int[] B) {
        int rotation = check(A[0], A, B, A.length);
        if (rotation != -1 || A[0] == B[0]) {
            return rotation;
        }
        return check(B[0], A, B, A.length);
    }

    private int check(int x, int[] a, int[] b, int len) {
        int rotationA = 0, rotationB = 0;
        for (int i = 0; i < len; i++) {
            if (a[i] != x && b[i] != x) {
                return -1;
            } else if (a[i] != x) {
                rotationA++;
            } else if (b[i] != x) {
                rotationB++;
            }
        }
        return Math.min(rotationA, rotationB);
    }
}
