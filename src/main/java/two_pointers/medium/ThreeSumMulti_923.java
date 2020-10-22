package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 923. 三数之和的多种可能
 *
 * 给定一个整数数组 A，以及一个整数 target 作为目标值，返回满足 i < j < k 且 A[i] + A[j] + A[k] == target 的元组 i, j, k 的数量。
 *
 * 由于结果会非常大，请返回 结果除以 10^9 + 7 的余数。
 *
 * 示例 1：
 *
 * 输入：A = [1,1,2,2,3,3,4,4,5,5], target = 8
 * 输出：20
 * 解释：
 * 按值枚举（A[i]，A[j]，A[k]）：
 * (1, 2, 5) 出现 8 次；
 * (1, 3, 4) 出现 8 次；
 * (2, 2, 4) 出现 2 次；
 * (2, 3, 3) 出现 2 次。
 * 示例 2：
 *
 * 输入：A = [1,1,2,2,2,2], target = 5
 * 输出：12
 * 解释：
 * A[i] = 1，A[j] = A[k] = 2 出现 12 次：
 * 我们从 [1,1] 中选择一个 1，有 2 种情况，
 * 从 [2,2,2,2] 中选出两个 2，有 6 种情况。
 *
 * 提示：
 *
 * 3 <= A.length <= 3000
 * 0 <= A[i] <= 100
 * 0 <= target <= 300
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-with-multiplicity
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ThreeSumMulti_923 {

    @Test
    public void test() {
        Assert.assertEquals(2,threeSumMulti(new int[]{3,3,2,0,2}, 7));
        Assert.assertEquals(20,threeSumMulti(new int[]{1,1,2,2,3,3,4,4,5,5}, 8));
        Assert.assertEquals(12,threeSumMulti(new int[]{1,1,2,2,2,2}, 5));
    }

    public int threeSumMulti(int[] A, int target) {
        //return fun(A,target);
        //return fun2(A,target);
        return fun3(A,target);
    }

    int MOD = 1000000000 + 7;

    private int fun3(int[] a,int target) {
        Arrays.sort(a);
        int len = a.length;
        int result = 0;
        for (int i = 0;i < len - 2;i++) {
            int left = i + 1;
            int right = len - 1;
            if (a[i] + a[i + 1] + a[i + 2] > target) {
                break;
            }
            if (a[i] + a[len - 2] + a[len - 1] < target) {
                continue;
            }
            while (left < right) {
                if (a[left] + a[right] == target - a[i]) {
                    if (a[left] != a[right]) {
                        int leftNum = 1,rightNum = 1;
                        while (left < right && a[left] == a[left + 1]) {
                            leftNum++;
                            left++;
                        }
                        while (left< right && a[right] == a[right - 1]) {
                            rightNum++;
                            right--;
                        }
                        result = (result + leftNum * rightNum) % MOD;
                    } else {
                        int temp = (right - left + 1) * (right - left) / 2;
                        result = (result + temp) % MOD;
                        while (left < right) left++;
                    }
                    left++;
                    right--;
                } else if (a[left] + a[right] < target - a[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    /**
     * 暴力-超时
     */
    private int fun2(int[] a,int target) {
        Arrays.sort(a);
        int result = 0;
        int len = a.length;
        for (int i = 0;i < len - 2;i++) {
            if (a[i] + a[i + 1] + a[i + 2] > target) {
                break;
            }
            if (a[i] + a[len - 2] + a[len - 1] < target) {
                continue;
            }
            for (int j = i + 1;j < len - 1;j++) {
                if (a[i] + a[j] + a[j + 1] > target) {
                    break;
                }
                if (a[i] + a[j] + a[len - 1] < target) {
                    continue;
                }
                for (int k = j + 1;k < len;k++) {
                    if (a[i] + a[j] + a[k] > target) {
                        break;
                    } else if (a[i] + a[j] + a[k] == target) {
                        result = (result + 1) % MOD;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 没有考虑重复的情况
     */
    private int fun(int[] a,int target) {
        int result = 0;
        Arrays.sort(a);
        for (int i = 0;i < a.length - 2;i++) {
            if (a[i] >= target) {
                break;
            }
            int left = i + 1;
            int right = a.length - 1;
            while (left < right) {
                // 后面两个判断有丢掉结果的可能
                if (a[left] + a[right] == target - a[i]) {
                    result = (result + 1) % MOD;
                    if (a[left] == a[left + 1]) {
                        left++;
                    } else if (a[right] == a[right - 1]) {
                        right--;
                    } else {
                        left++;
                        right--;
                    }
                } else if (a[left] + a[right] > target - a[i]) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }
}
