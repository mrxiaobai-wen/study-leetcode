package daily_question.daily_question_2020;

/**
 * 面试题 10.01. 合并排序的数组
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 * <p>
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 */
public class SortArrayMerge {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 0, 0, 0};
        int[] B = new int[]{2, 5, 6};
        SortArrayMerge ceshi = new SortArrayMerge();
        ceshi.merge(A, 3, B, 3);
    }

    public void merge(int[] A, int m, int[] B, int n) {
        fun1(A, m, B, n);
    }

    private void fun1(int[] A, int m, int[] B, int n) {
        int aIndex = m - 1;
        int bIndex = n - 1;
        int curIndex = m + n - 1;
        for (; curIndex >= 0 && aIndex >= 0 && bIndex >= 0; curIndex--) {
            if (A[aIndex] > B[bIndex]) {
                A[curIndex] = A[aIndex];
                A[aIndex] = 0;
                aIndex--;
            } else {
                A[curIndex] = B[bIndex];
                bIndex--;
            }
        }
        while (bIndex >= 0 && curIndex >= 0) {
            A[curIndex] = B[bIndex];
            bIndex--;
            curIndex--;
        }
    }
}
