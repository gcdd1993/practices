package io.github.gcdd1993.chapter4;

import jdk.nashorn.internal.ir.BinaryNode;

/**
 * Created by gcdd1993 on 2021/3/15.
 */
public class BinaryTree {
    private Object element;
    private BinaryNode left;
    private BinaryNode right;

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }
}
