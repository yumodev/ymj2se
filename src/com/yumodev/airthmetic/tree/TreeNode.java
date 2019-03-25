package com.yumodev.airthmetic.tree;

//子节点的定义
public class TreeNode {

    public int value;
    public char key;
    public TreeNode lChild = null;
    public TreeNode rChild = null;
    public TreeNode() {};

    public TreeNode(int value) {
        this.value = value;
        this.lChild = null;
        this.rChild = null;
    }

    public TreeNode(int value, TreeNode lChild, TreeNode rChild) {
        this.value = value;
        this.lChild = lChild;
        this.rChild = rChild;
    }


    public void SetNode(int value, TreeNode lChild, TreeNode rChild) {
        this.value = value;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    public String toString() {
        return String.valueOf(value);
    }
}