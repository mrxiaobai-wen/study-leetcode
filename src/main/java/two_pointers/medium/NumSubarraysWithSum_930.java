package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 930. 和相同的二元子数组
 *
 * 在由若干 0 和 1  组成的数组 A 中，有多少个和为 S 的非空子数组。
 *
 * 示例：
 *
 * 输入：A = [1,0,1,0,1], S = 2
 * 输出：4
 * 解释：
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 *
 * 提示：
 *
 * A.length <= 30000
 * 0 <= S <= A.length
 * A[i] 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumSubarraysWithSum_930 {

    @Test
    public void test() {
        Assert.assertEquals(4,numSubarraysWithSum(new int[]{1,0,1,0,1},2));
        Assert.assertEquals(15,numSubarraysWithSum(new int[]{0,0,0,0,0},0));
    }

    public int numSubarraysWithSum(int[] A, int S) {
        //return fun(A,S);
        //return fun1(A,S);
        //return fun2(A,S);
        return fun3(A,S);
    }

    /**
     * 参考思路：三指针
     * todo 没怎么看懂
     */
    private int fun3(int[] arr,int s) {
        int iLo = 0, iHi = 0;
        int sumLo = 0, sumHi = 0;
        int ans = 0;

        for (int j = 0; j < arr.length; ++j) {
            // While sumLo is too big, iLo++
            sumLo += arr[j];
            while (iLo < j && sumLo > s)
                sumLo -= arr[iLo++];

            // While sumHi is too big, or equal and we can move, iHi++
            sumHi += arr[j];
            while (iHi < j && (sumHi > s || sumHi == s && arr[iHi] == 0))
                sumHi -= arr[iHi++];

            if (sumLo == s)
                ans += iHi - iLo + 1;
        }

        return ans;
    }

    /**
     * 参考思路：前缀和!!!!!
     * 设数组 P 为数组 A 的前缀和，即：
     *
     * P[i] = A[0] + A[1] + ... + A[i - 1]
     *
     * 这样就可以通过 P[j + 1] - P[i] = A[i] + A[i + 1] + ... + A[j] 快速计算出 A[i..j] 的和。
     *
     * 我们可以对数组 P 进行一次线性扫描，当扫描到 P[j] 时，我们需要得到的是满足 P[j] = P[i] + S 且 i < j 的 i 的数目，使用计数器（map 或 dict）可以满足要求。
     */
    private int fun2(int[] a,int s) {
        int N = a.length;
        int[] P = new int[N + 1];
        for (int i = 0; i < N; ++i)
            P[i+1] = P[i] + a[i];

        Map<Integer, Integer> count = new HashMap();
        int ans = 0;
        for (int x: P) {
            ans += count.getOrDefault(x, 0);
            count.put(x+s, count.getOrDefault(x+s, 0) + 1);
        }

        return ans;
    }

    private int fun1(int[] a,int s) {
        int count = 0;
        List<Integer> list = new ArrayList<>();
        // 头尾添加两个哨兵，降低边界值处理复杂度
        list.add(-1);
        for (int i = 0;i < a.length;i++) {
            if (a[i] == 1) {
                count++;
                list.add(i);
            }
        }
        // 头尾添加两个哨兵，降低边界值处理复杂度
        list.add(a.length);
        if (count < s) {
            return 0;
        }
        int result = 0;
        if (s == 0) {
            for (int i = 0;i < list.size() - 1;i++) {
                // 中间隔了多少个0
                int w = list.get(i + 1) - list.get(i) - 1;
                result += w * (w + 1) / 2;
            }
            return result;
        }
        for (int i = 1;i + s <= count + 1;i++) {
            int preIndex = list.get(i - 1);
            int curIndex = list.get(i);
            int lastPreIndex = list.get(i + s - 1);
            int lastIndex = list.get(i + s);
            result += (curIndex - preIndex) * (lastIndex - lastPreIndex);
        }
        return result;
    }

    /**
     * 思路错误！！！
     */
    private int fun(int[] a,int s) {
        int result = 0;
        int curSum = 0;
        for (int left = 0,right = 0;left < a.length;) {
            if (right < a.length && a[right] == 1) {
                curSum++;
            }
            if (curSum == s) {
                result++;
                if (a[left] == 0) {
                    left++;
                } else {
                    if (right >= a.length) {
                        break;
                    }
                }
            } else if (curSum > s) {
                if (a[left] == 1) {
                    curSum--;
                }
                left++;
            } else if (right >= a.length) {
                break;
            }
            if (right < a.length) {
                right++;
            }
        }
        return result;
    }
}
