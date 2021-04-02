package io.github.gcdd1993.chapter7;

/**
 * 希尔排序
 * <p>
 * Created by gcdd1993 on 2021/4/2.
 */
public class ShellSort {

    public static <T extends Comparable<? super T>> void shellSort(T[] arr) {
        int j;
        // 每次迭代，都将进行二分
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                T tmp = arr[i];
                for (j = i; j >= gap && tmp.compareTo(arr[j - gap]) < 0; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        var arr = new Integer[]{2, 3, 1, 321321, 11, 231, 3, 4, 1321};
        ShellSort.shellSort(arr);
        for (Integer integer : arr) {
            System.out.print(integer + ", ");
        }
    }

}
