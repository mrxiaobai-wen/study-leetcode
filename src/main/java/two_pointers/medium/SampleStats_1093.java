package two_pointers.medium;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1093. 大样本统计
 *
 * 我们对 0 到 255 之间的整数进行采样，并将结果存储在数组 count 中：count[k] 就是整数 k 的采样个数。
 *
 * 我们以 浮点数 数组的形式，分别返回样本的最小值、最大值、平均值、中位数和众数。其中，众数是保证唯一的。
 *
 * 我们先来回顾一下中位数的知识：
 *
 * 如果样本中的元素有序，并且元素数量为奇数时，中位数为最中间的那个元素；
 * 如果样本中的元素有序，并且元素数量为偶数时，中位数为中间的两个元素的平均值。
 *
 * 示例 1：
 *
 * 输入：count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * 输出：[1.00000,3.00000,2.37500,2.50000,3.00000]
 * 示例 2：
 *
 * 输入：count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * 输出：[1.00000,4.00000,2.18182,2.00000,1.00000]
 *
 * 提示：
 *
 * count.length == 256
 * 1 <= sum(count) <= 10^9
 * 计数表示的众数是唯一的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/statistics-from-a-large-sample
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SampleStats_1093 {

    @Test
    public void test() {
        int[] count = new int[256];
        count[1] = 1;
        count[2] = 3;
        count[3] = 4;
        sampleStats(count);
    }

    public double[] sampleStats(int[] count) {
        return fun(count);
    }

    /**
     * 最小值、最大值、平均值、中位数和众数
     */
    private double[] fun(int[] count) {
        double[] result = new double[5];
        int min = -1,max = 0;
        int num = 0;
        double sum = 0;
        int maxIndex = 0;
        for (int i = 0;i < count.length;i++) {
            int cur = count[i];
            if (cur == 0) {
                continue;
            }
            if (min == -1) {
                min = i;
            }
            if (cur > count[maxIndex]) {
                maxIndex = i;
            }
            max = i;
            num += cur;
            sum += i * cur;
        }
        result[0] = min;
        result[1] = max;
        result[2] = sum / num;
        result[4] = maxIndex;
        // 处理中位数
        int left = 0,right = 0;
        if (num % 2 == 0) {
            right = num / 2;
            left = right - 1;
        } else {
            left = right = num /2;
        }
        int tempNum = 0;
        int leftNumber = 0,rightNumber = 0;
        for (int i = 0;i < count.length;i++) {
            if (count[i] == 0) {
                continue;
            }
            int newTempNum = tempNum + count[i];
            if (tempNum < left && left <= newTempNum) {
                leftNumber = i;
            }
            if (tempNum <= right && right < newTempNum) {
                rightNumber = i;
            }
            tempNum = newTempNum;
        }
        result[3] = (leftNumber + rightNumber) / 2.0;
        return result;
    }
}
