package two_pointers.simple;

import org.junit.Test;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 *
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Merge_88 {

    @Test
    public void test() {
        int[] arr1 = new int[]{1,2,3,0,0,0};
        int[] arr2 = new int[]{2,5,6};
        merge(arr1,3,arr2,3);
        System.out.println(Arrays.toString(arr1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        fun(nums1,m,nums2,n);
    }

    private void fun(int[] nums1,int m,int[] nums2,int n) {
        int len = m + n - 1;
        int i = m - 1,j = n - 1;
        for (;i >= 0 && j >= 0;) {
            if (nums1[i] >= nums2[j]) {
                nums1[len] = nums1[i];
                i--;
            } else {
                nums1[len] = nums2[j];
                j--;
            }
            len--;
        }
        while(j >= 0) {
            nums1[len] = nums2[j];
            j--;
            len--;
        }
    }
}
