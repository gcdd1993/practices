package io.github.gcdd1993.chapter6;

/**
 * 二叉堆（最小堆）
 * <p>
 * 最小堆：父节点的键值总是小于等于子节点
 * 最大堆：父节点的键值总是大于等于子节点
 *
 * <p>
 * Created by gcdd1993 on 2021/4/2.
 */
public class BinaryHeap<T extends Comparable<T>> {
    // 默认容量
    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize; // Number of elements in heap
    private T[] array;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        currentSize = 0;
        // 为什么要容量基础上+1？
        array = (T[]) new Comparable[capacity + 1];
    }

    public BinaryHeap(T[] items) {
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for (T item : items)
            array[i++] = item;
        buildHeap();
    }

    /**
     * 插入新元素
     * <p>
     * 上滤
     *
     * @param x 新元素
     */
    public void insert(T x) {
        // 如果容量不够插入新元素，扩容为原来的2倍 + 1
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }
        int hole = ++currentSize;
        for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2) {
            array[hole] = array[hole / 2];

        }
        array[hole] = x;
    }

    public T findMin() {
        if (isEmpty()) {
            throw new IllegalArgumentException("当前堆为空");
        }
        // 下标为1的节点，即为二叉堆的根节点，根据堆序性质，根节点为最小节点
        return array[1];
    }

    /**
     * 删除最小元,根据堆序性质，最小元在根节点
     * <p>
     * 使用下滤方式删除
     *
     * @return
     */
    public T deleteMin() {
        if (isEmpty()) {
            throw new IllegalArgumentException("当前堆为空");
        }
        T minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
//        array = (T[]) new Comparable[0];
    }

    private void percolateDown(int hole) {
        int child;
        T tmp = array[hole];
        for (; hole >> 1 <= currentSize; hole = child) {
            child = hole >> 1;
            if (child != currentSize &&
                    array[child + 1].compareTo(array[child]) < 0)
                child++;
            if (array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    private void buildHeap() {
        for (int i = currentSize << 1; i > 0; i--) {
            percolateDown(i);
        }
    }

    private void enlargeArray(int newSize) {
    }
}
