package io.github.gcdd1993.chapter7;

/**
 * 归并排序，递归合并两个已排序的数组
 * <p>
 * Created by gcdd1993 on 2021/4/2.
 */
public class MergeSort {

    public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
        // 建立临时数组，用于存放中间结果
        T[] tmpArr = (T[]) new Comparable[arr.length];
        mergeSort(arr, tmpArr, 0, arr.length - 1);
    }

    private static <T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmpArr, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(arr, tmpArr, left, center); // 拆分进行排序
            mergeSort(arr, tmpArr, center + 1, right); // 拆分进行排序
            merge(arr, tmpArr, left, center + 1, right);
        }
    }

    /**
     * 合并两个已排序的数组
     *
     * @param arr      原始数组
     * @param tmpArr   临时数组
     * @param leftPos  左起点
     * @param rightPos 右起点
     * @param rightEnd 右结束点
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void merge(T[] arr, T[] tmpArr, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (arr[leftPos].compareTo(arr[rightPos]) <= 0)
                tmpArr[tmpPos++] = arr[leftPos++];
            else
                tmpArr[tmpPos++] = arr[rightPos++];
        }
        while (leftPos <= leftEnd)
            tmpArr[tmpPos++] = arr[leftPos++]; // 复制剩余的左数组所有项
        while (rightPos <= rightEnd)
            tmpArr[tmpPos++] = arr[rightPos++]; // 复制剩余的右数组所有项

        // 将临时数组所有项复制到arr
        for (int i = 0; i < numElements; i++, rightEnd--) {
            arr[rightEnd] = tmpArr[rightEnd];
        }
    }

    public static void main(String[] args) {
        var arr = new Integer[]{2, 3, 1, 321321, 11, 231, 3, 4, 1321};
        MergeSort.mergeSort(arr);
        for (Integer integer : arr) {
            System.out.print(integer + ", ");
        }
    }
}
