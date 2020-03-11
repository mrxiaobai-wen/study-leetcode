package daily_question;

import org.junit.Test;

/**
 * 1013. 将数组分成和相等的三个部分
 * 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
 * <p>
 * 形式上，如果可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输出：[0,2,1,-6,6,-7,9,1,2,0,1]
 * 输出：true
 * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * 示例 2：
 * <p>
 * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：[3,3,6,5,-2,2,5,1,-9,4]
 * 输出：true
 * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= A.length <= 50000
 * -10^4 <= A[i] <= 10^4
 */
public class CanThreePartsEqualSum_1013 {
    @Test
    public void test() {
        fun(new int[]{0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1});
    }

    public boolean canThreePartsEqualSum(int[] A) {
        return fun(A);
    }

    private boolean fun(int[] A) {
        int temp = 0;
        for (int i = 0; i < A.length; i++) {
            temp += A[i];
        }
        if (temp % 3 != 0) {
            return false;
        }
        int part = temp / 3;
        int index1 = -1;
        int index2 = -1;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            if (sum == part && index1 < 0) {
                index1 = i;
                sum = 0;
            } else if (sum == part && index2 < 0) {
                index2 = i;
                break;
            }
        }
        if (index1 >= 0 && index2 > index1 && index2 < A.length - 1) {
            return true;
        }
        return false;
    }
}
