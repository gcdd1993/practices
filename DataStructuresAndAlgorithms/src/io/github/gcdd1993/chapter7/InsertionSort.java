package io.github.gcdd1993.chapter7;

/**
 * 插入排序
 * <p>
 * Created by gcdd1993 on 2021/4/2.
 */
public class InsertionSort {

    public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
        int j;
        for (int p = 1; p < arr.length; p++) {
            T tmp = arr[p];
            for (j = p; j > 0 && tmp.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {
        var arr = new Integer[]{2, 3, 1, 321321, 11, 231, 3, 4, 1321};
        InsertionSort.insertionSort(arr);
        for (Integer integer : arr) {
            System.out.print(integer + ", ");
        }
    }

}
