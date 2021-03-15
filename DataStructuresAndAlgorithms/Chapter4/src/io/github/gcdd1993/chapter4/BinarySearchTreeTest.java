package io.github.gcdd1993.chapter4;

/**
 * Created by gcdd1993 on 2021/3/15.
 */
public class BinarySearchTreeTest {

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(100);
        tree.insert(101);
        tree.insert(102);
        tree.insert(103);
        tree.insert(104);
        tree.insert(10);

        tree.printTree();

        System.out.println(tree.contains(10));
        System.out.println(tree.contains(11));
        System.out.println(tree.findMax());
        System.out.println(tree.findMin());

        tree.remove(10);
        System.out.println(tree.contains(10));
    }
}
