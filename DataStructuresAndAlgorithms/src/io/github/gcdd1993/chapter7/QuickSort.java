package io.github.gcdd1993.chapter7;

/**
 * 快速排序
 * <p>
 * Created by gcdd1993 on 2021/4/2.
 */
public class QuickSort {
    private static final int CUTOFF = 10;

    public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] arr, int left, int right) {
        if (right - left <= 2) {
            return;
        }
//        if (left + CUTOFF <= right) {
        T pivot = median3(arr, left, right);
        System.out.println("pivot: " + pivot);

        // begin partitioning
        int i = left, j = right - 1;
        for (; ; ) {
            while (arr[++i].compareTo(pivot) < 0) {
            }
            while (arr[--j].compareTo(pivot) > 0) {
            }
            if (i < j) {
                swapReferences(arr, i, j);
            } else {
                break;
            }
        }
        swapReferences(arr, i, right - 1); // restore pivot

        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
//        } else {
//            // 使用插入排序，对于很小的数组（N <= 20），快速排序不如插入排序
//            insertionSort(arr, left, right);
//        }
    }

    /**
     * 三数中值分割法，选取枢纽元
     *
     * @param <T>
     * @return
     */
    private static <T extends Comparable<? super T>> T median3(T[] arr, int left, int right) {
        int center = (left + right) / 2;
        if (arr[center].compareTo(arr[left]) < 0)
            swapReferences(arr, left, center);
        if (arr[right].compareTo(arr[left]) < 0)
            swapReferences(arr, left, right);
        if (arr[right].compareTo(arr[center]) < 0)
            swapReferences(arr, center, right);

        swapReferences(arr, center, right - 1);
        return arr[right - 1];
    }

    /**
     * 交换顺序
     *
     * @param arr
     * @param a
     * @param b
     */
    private static <T extends Comparable<? super T>> void swapReferences(T[] arr, int a, int b) {
        T tmp = arr[a];
        arr[b] = arr[a];
        arr[a] = tmp;
    }

    private static <T extends Comparable<? super T>> void insertionSort(T[] arr, int left, int right) {
        int j;
        for (int p = left + 1; p <= right; p++) {
            T tmp = arr[p];
            for (j = p; j > left && tmp.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
        for (T item : arr) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        var arr = new Integer[]{2, 3, 1, 321321, 11, 231, 3, 4, 1321, 1321, 32, 4, 13, 21, 3, 24, 1, 32, 14, 21, 32, 1, 421, 32, 14, 21, 321, 4, 21, 32, 14};
//        var arr = new Integer[]{2, 3, 1, 321321, 11, 231, 3, 4};
        QuickSort.quickSort(arr);
        for (Integer integer : arr) {
            System.out.print(integer + ", ");
        }
    }

}
