package io.github.gcdd1993.chapter4.btree;

/**
 * B树的实现，参考自：https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BTree.java.html
 * Created by gcdd1993 on 2021/3/16.
 */
public class BTree<K extends Comparable<? super K>, V> {
    // B树的阶数，每个叶子节点最大子树个数 = M - 1，最少为2
    private static final int M = 4;

    private Node<K, V> root;
    private int height;
    private int n; // 键值对数

    public BTree() {
        root = new Node<>(0);
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns the height of this B-tree (for debugging).
     *
     * @return the height of this B-tree
     */
    public int height() {
        return height;
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null}, this effectively deletes the key from the symbol table.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("argument key to put() is null");
        Node<K, V> u = insert(root, key, val, height);
        n++;
        if (u == null) return;

        // need to split root
        Node<K, V> t = new Node<>(2);
        t.children[0] = new Entry<>(root.children[0].key, null, root);
        t.children[1] = new Entry<>(u.children[0].key, null, u);
        root = t;
        height++;
    }

    /**
     * B树插入的主逻辑
     */
    private Node<K, V> insert(Node<K, V> h, K key, V val, int ht) {
        int j;
        Entry<K, V> t = new Entry<>(key, val, null);

        // 叶子节点
        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(key, h.children[j].key)) break;
            }
        }

        // 内部节点
        else {
            for (j = 0; j < h.m; j++) {
                if (j + 1 == h.m || less(key, h.children[j + 1].key)) {
                    Node<K, V> u = insert(h.children[j++].next, key, val, ht - 1);
                    if (u == null) return null;

                    // B树自平衡操作
                    t.key = u.children[0].key;
                    t.val = null;
                    t.next = u;
                    break;
                }
            }
        }

        for (int i = h.m; i > j; i--) {
            h.children[i] = h.children[i - 1];
        }
        h.children[j] = t;
        h.m++;
        if (h.m < M) {
            return null;
        } else {
            // 不满足B树的定义：
            return split(h);
        }
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     * and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return search(root, key, height);
    }

    /**
     * 在树种搜索节点x
     *
     * @param x  带搜索的节点
     * @param k  关键字
     * @param ht 高度
     * @return v
     */
    private V search(Node<K, V> x, K k, int ht) {
        Entry<K, V>[] children = x.children;
        // 叶子节点
        if (ht == 0) {
            for (int i = 0; i < x.m; i++) {
                if (eq(k, children[i].key)) return children[i].val;
            }
        }

        // 内部节点
        else {
            for (int i = 0; i < x.m; i++) {
                // 递归搜索，每次递归，高度将减小1，所以递归的次数是有限集
                if (i + 1 == x.m || less(k, children[i + 1].key)) {
                    return search(children[i].next, k, ht - 1);
                }
            }
        }
        return null;
    }

    private String toString(Node<K, V> h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry<K, V>[] children = h.children;

        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s.append(indent).append(children[j].key).append(" ").append(children[j].val).append("\n");
            }
        } else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) s.append(indent).append("(").append(children[j].key).append(")\n");
                s.append(toString(children[j].next, ht - 1, indent + "     "));
            }
        }
        return s.toString();
    }


    // comparison functions - make Comparable instead of Key to avoid casts
    private boolean less(K k1, K k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(K k1, K k2) {
        return k1.compareTo(k2) == 0;
    }

    // split node in half
    private Node<K, V> split(Node<K, V> h) {
        Node<K, V> t = new Node<>(M / 2);
        h.m = M / 2;
        for (int j = 0; j < M / 2; j++)
            t.children[j] = h.children[M / 2 + j];
        return t;
    }

    @Override
    public String toString() {
        return toString(root, height, "") + "\n";
    }

    /**
     * B树的节点
     *
     * @param <K> 索引
     * @param <V> 数据
     */
    private static class Node<K, V> {
        private int m; // number of children
        private final Entry<K, V>[] children = new Entry[M]; // 子节点

        public Node(int m) {
            this.m = m;
        }
    }

    /**
     * internal nodes（内部节点）: only use key and next
     * external nodes（叶子节点）: only use key and value
     *
     * @param <K>
     * @param <V>
     */
    private static class Entry<K, V> {
        private K key;
        private V val;
        private Node<K, V> next; // 迭代数组条目的辅助字段。

        public Entry(K key, V val, Node<K, V> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

}
