package com.joyance.demo.base.sort;

public class BinaryNode {
	
	private int data;  
    private BinaryNode leftNode;  
    private BinaryNode rightNode;  
    
    public BinaryNode(int data, BinaryNode leftNode, BinaryNode rightNode){  
        this.data = data;  
        this.leftNode = leftNode;  
        this.rightNode = rightNode;  
    }  
    public int getData() {  
        return data;  
    }  
    public void setData(int data) {  
        this.data = data;  
    }  
    public BinaryNode getLeftNode() {  
        return leftNode;  
    }  
    public void setLeftNode(BinaryNode leftNode) {  
        this.leftNode = leftNode;  
    }  
    public BinaryNode getRightNode() {  
        return rightNode;  
    }  
    public void setRightNode(BinaryNode rightNode) {  
        this.rightNode = rightNode;  
    }  
}
