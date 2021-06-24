package sort.algorithm;

import org.junit.Test;

/**
 * 排序算法汇总
 */
public class SortAlgorithm {

    /**
     * 数组升序检测
     */
    public static boolean arrSortCheck(int[] arr) {
        for (int i = 0;i < arr.length - 1;i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // -------------------------- 插入排序 --------------------------

    @Test
    public void insertSortTest() {
        int[] arr = new int[]{10, 100, 50, 60, 70, 30, 20};
        insertSort(arr);
        assert arrSortCheck(arr);
    }

    /**
     * 插入排序-插入排序
     *
     * 思路：从前往后开始排序，假定前面已经排序好，然后每次选取一个新元素插入到前面已经排好序的列表中。
     */
    public static void insertSort(int[] arr) {
        for (int i = 1;i < arr.length;i++) {
            int temp = arr[i];
            int j = i;
            for (;j > 0 && temp < arr[j - 1];j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    @Test
    public void shellSortTest() {
        int[] arr = new int[]{10, 100, 50, 60, 70, 30, 20};
        shellSort(arr);
        assert arrSortCheck(arr);
    }

    /**
     * 插入排序-希尔排序
     *
     * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序，同时该算法是冲破O(n2）的第一批算法之一。
     *
     * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
     *
     * 希尔排序，也称 递减增量排序算法，是插入排序的一种更高效的改进版本。希尔排序是 非稳定排序算法。
     *
     * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
     * 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率
     * 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一步
     */
    public static void shellSort(int[] arr) {
        int len = arr.length;
        for (int gap = len / 2;gap >= 1;gap /= 2) {
            for (int i = gap;i < len;i++) {
                int temp = arr[i];
                int j = i;
                for (;j >= gap && temp < arr[j - gap];j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    // -------------------------- 选择排序 --------------------------

    @Test
    public void selectSortTest() {
        int[] arr = new int[]{10, 100, 50, 60, 70, 30, 20};
        selectSort(arr);
        assert arrSortCheck(arr);
    }

    /**
     * 选择排序-选择排序
     */
    public static void selectSort(int[] arr) {
        for (int i = 0;i < arr.length - 1;i++) {
            int minIndex = i;
            for (int j = i + 1;j < arr.length;j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    @Test
    public void heapSortTest() {
        int[] arr = new int[]{10, 100, 50, 60, 70, 30, 20};
        heapSort(arr);
        assert arrSortCheck(arr);
    }

    /**
     * 选择排序-堆排序
     */
    public static void heapSort(int[] arr) {
        int len = arr.length;
        for (int i = len - 1;i > 0;i--) {
            maxHeapfy(arr,i);
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
        }
    }

    private static void maxHeapfy(int[] arr,int len) {
        for (int i = (len - 1) / 2;i >= 0;i--) {
            int child = 2 * i + 1;
            if (child != len && arr[child] < arr[child + 1]) {
                child++;
            }
            if (arr[i] < arr[child]) {
                int temp = arr[i];
                arr[i] = arr[child];
                arr[child] = temp;
            }
        }
    }

    // -------------------------- 交换排序 --------------------------

    @Test
    public void bubbleSortTest() {
        int[] arr = new int[]{10, 100, 50, 60, 70, 30, 20};
        bubbleSort(arr);
        assert arrSortCheck(arr);
    }

    /**
     * 交换排序-冒泡排序
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0;i < arr.length - 1;i++) {
            for (int j = 0;j < arr.length - 1 - i;j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    @Test
    public void quickSortTest() {
        int[] arr = new int[]{10, 100, 50, 60, 70, 30, 20};
        quickSort(arr,0,arr.length - 1);
        assert arrSortCheck(arr);
    }

    /**
     * 交换排序-快速排序
     *
     * 快速排序的基本思想：挖坑填数+分治法。
     *
     * 从数列中挑出一个元素，称为"基准"（pivot）。
     * 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
     * 递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     */
    public static void quickSort(int[] arr,int low,int high) {
        if (low >= high) {
            return;
        }
        int left = low,right = high;
        int movedVal = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= movedVal) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= movedVal) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = movedVal;
        quickSort(arr,low,left - 1);
        quickSort(arr,left + 1,high);
    }

    // -------------------------- 归并排序 --------------------------

    /*
    归并排序：
    归并排序算法是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。

    归并排序可通过两种方式实现:
    1、自上而下的递归
    2、自下而上的迭代

    递归法（假设序列共有n个元素）：
    1、将序列每相邻两个数字进行归并操作，形成 floor(n/2)个序列，排序后每个序列包含两个元素；
    2、将上述序列再次归并，形成 floor(n/4)个序列，每个序列包含四个元素；
    3、重复步骤2，直到所有元素排序完毕。

    迭代法：
    1、申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
    2、设定两个指针，最初位置分别为两个已经排序序列的起始位置
    3、比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
    4、重复步骤3直到某一指针到达序列尾，将另一序列剩下的所有元素直接复制到合并序列尾
     */
    @Test
    public void mergeSortTest() {
        int[] arr = new int[]{10, 100, 50, 60, 70, 30, 20};
        mergeSort(arr,0,arr.length - 1);
        assert arrSortCheck(arr);
    }

    /**
     * 归并排序
     *
     * 分解：将序列每次折半拆分
     * 合并：将划分后的序列段两两排序合并
     */
    public static void mergeSort(int[] arr,int low,int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) / 2;
        mergeSort(arr,low,mid);
        mergeSort(arr,mid + 1,high);
        mergeArr(arr,low,mid,high);
    }

    private static void mergeArr(int[] arr,int low,int mid,int high) {
        int[] temp = new int[high - low + 1];
        int list1 = low,list2 = mid + 1;
        int i = 0;
        for (;i < temp.length && list1 <= mid && list2 <= high;i++) {
            temp[i] = arr[list1] <= arr[list2] ? arr[list1++] : arr[list2++];
        }
        while (list1 <= mid) {
            temp[i++] = arr[list1++];
        }
        while (list2 <= high) {
            temp[i++] = arr[list2++];
        }
        for (int k = 0;k < temp.length;k++) {
            arr[low + k] = temp[k];
        }
    }

    // -------------------------- 基数排序 --------------------------

    /**
     * 基数排序
     */
    public static void radixSort() {

    }
}
