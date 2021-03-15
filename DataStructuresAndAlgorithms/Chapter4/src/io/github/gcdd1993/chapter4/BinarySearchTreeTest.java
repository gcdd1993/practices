package io.github.gcdd1993.chapter4;

/**
 * Created by gcdd1993 on 2021/3/15.
 */
public class BinarySearchTreeTest {

    public static void main(String[] args) {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("节点1");
        tree.insert("节点2");
        tree.insert("节点3");
        tree.insert("节点4");
        tree.insert("节点5");

        tree.printTree();
    }
}
