package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 838. 推多米诺
 * <p>
 * 一行中有 N 张多米诺骨牌，我们将每张多米诺骨牌垂直竖立。
 * <p>
 * 在开始时，我们同时把一些多米诺骨牌向左或向右推。
 * <p>
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。
 * <p>
 * 同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * <p>
 * 如果同时有多米诺骨牌落在一张垂直竖立的多米诺骨牌的两边，由于受力平衡， 该骨牌仍然保持不变。
 * <p>
 * 就这个问题而言，我们会认为正在下降的多米诺骨牌不会对其它正在下降或已经下降的多米诺骨牌施加额外的力。
 * <p>
 * 给定表示初始状态的字符串 "S" 。如果第 i 张多米诺骨牌被推向左边，则 S[i] = 'L'；如果第 i 张多米诺骨牌被推向右边，则 S[i] = 'R'；
 * 如果第 i 张多米诺骨牌没有被推动，则 S[i] = '.'。
 * <p>
 * 返回表示最终状态的字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 * 示例 2：
 * <p>
 * 输入："RR.L"
 * 输出："RR.L"
 * 说明：第一张多米诺骨牌没有给第二张施加额外的力。
 * 提示：
 * <p>
 * 0 <= N <= 10^5
 * 表示多米诺骨牌状态的字符串只含有 'L'，'R'; 以及 '.';
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/push-dominoes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PushDominoes_838 {

    @Test
    public void test() {
        Assert.assertEquals("LL.RR.LLRRLL..", pushDominoes(".L.R...LR..L.."));
        Assert.assertEquals("RR.L", pushDominoes("RR.L"));
    }

    public String pushDominoes(String dominoes) {
        return fun(dominoes);
    }

    /**
     * todo
     * 参考答案-计算受力
     */
    public String fun2(String S) {
        char[] A = S.toCharArray();
        int N = A.length;
        int[] forces = new int[N];

        // Populate forces going from left to right
        int force = 0;
        for (int i = 0; i < N; ++i) {
            if (A[i] == 'R') force = N;
            else if (A[i] == 'L') force = 0;
            else force = Math.max(force - 1, 0);
            forces[i] += force;
        }

        // Populate forces going from right to left
        force = 0;
        for (int i = N - 1; i >= 0; --i) {
            if (A[i] == 'L') force = N;
            else if (A[i] == 'R') force = 0;
            else force = Math.max(force - 1, 0);
            forces[i] -= force;
        }

        StringBuilder ans = new StringBuilder();
        for (int f : forces)
            ans.append(f > 0 ? 'R' : f < 0 ? 'L' : '.');
        return ans.toString();
    }

    /**
     * 超时！！！
     */
    private String fun(String dominoes) {
        char[] arr = dominoes.toCharArray();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '.') {
                list.add(i);
            }
        }
        if (list.isEmpty()) {
            return dominoes;
        }
        // 最左端
        for (int i = list.get(0) - 1; i >= 0 && arr[list.get(0)] == 'L'; i--) {
            arr[i] = 'L';
        }
        for (int i = list.get(list.size() - 1) + 1; i < arr.length && arr[list.get(list.size() - 1)] == 'R'; i++) {
            arr[i] = 'R';
        }
        for (int i = 0; i < list.size() - 1 && list.size() >= 2; i++) {
            int left = list.get(i);
            int right = list.get(i + 1);
            if (arr[left] == 'R' && arr[right] == 'L') {
                // 双向向中间倒
                int leftIndex = left + 1;
                int rightIndex = right - 1;
                while (leftIndex < rightIndex) {
                    arr[leftIndex] = arr[left];
                    arr[rightIndex] = arr[right];
                    leftIndex++;
                    rightIndex--;
                }
            } else if (arr[left] == 'R') {
                // 中间整体向右倒
                for (int temp = left + 1; temp < right; temp++) {
                    arr[temp] = 'R';
                }
            } else if (arr[right] == 'L') {
                // 中间整体向左倒
                for (int temp = right - 1; temp > left; temp--) {
                    arr[temp] = 'L';
                }
            } else {
                continue;
            }
        }
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        return result;
    }
}
