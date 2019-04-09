package com.joyance.demo.base.sort;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

	public List<Integer> qianxuNumList = new ArrayList<Integer>();
	
	 /** 
     * @author yaobo
     * 二叉树的先序中序后序排序 
     */  
    public BinaryNode init() {//注意必须逆序建立，先建立子节点，再逆序往上建立，因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错  
        BinaryNode J = new BinaryNode(8, null, null);  
        BinaryNode H = new BinaryNode(4, null, null);  
        BinaryNode G = new BinaryNode(2, null, null);  
        BinaryNode F = new BinaryNode(7, null, J);  
        BinaryNode E = new BinaryNode(5, H, null);  
        BinaryNode D = new BinaryNode(1, null, G);  
        BinaryNode C = new BinaryNode(9, F, null);  
        BinaryNode B = new BinaryNode(3, D, E);  
        BinaryNode A = new BinaryNode(6, B, C);  
        return A;   //返回根节点  
    }
    
    // 用递归的方法进行前序遍历
    public void qinaxuDigui(BinaryNode treeNode) {
        qianxuNumList.add(treeNode.getData());
        if (treeNode.getLeftNode() != null) {
            qinaxuDigui(treeNode.getLeftNode());
        }
        if (treeNode.getRightNode() != null) {
            qinaxuDigui(treeNode.getRightNode());
        }
    }
    
    // 用递归的方法进行中序遍历
    public void zhongxuDigui(BinaryNode treeNode) {
        if (treeNode.getLeftNode() != null) {
            zhongxuDigui(treeNode.getLeftNode());
        }
        qianxuNumList.add(treeNode.getData());
        if (treeNode.getRightNode() != null) {
            zhongxuDigui(treeNode.getRightNode());
        }
    }

    // 用递归的方法进行后序遍历
    public void houxuDigui(BinaryNode treeNode) {
        if (treeNode.getLeftNode() != null) {
            houxuDigui(treeNode.getLeftNode());
        }
        if (treeNode.getRightNode() != null) {
            houxuDigui(treeNode.getRightNode());
        }
        qianxuNumList.add(treeNode.getData());
    }
    
    public static void main(String[] args) {
    	BinaryTree tree = new BinaryTree();
    	BinaryNode root = tree.init();
    	tree.qinaxuDigui(root);
    	for(Integer val:tree.qianxuNumList){
    		System.out.println(val);
    	}
	}
	
}
