package com.joyance.demo.base.sort;

public class BinTreeNode {

	private String data;
	private BinTreeNode leftChild;
	private BinTreeNode rightChild;
	private BinTreeNode parent;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public BinTreeNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(BinTreeNode leftChild) {
		this.leftChild = leftChild;
	}
	public BinTreeNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(BinTreeNode rightChild) {
		this.rightChild = rightChild;
	}
	public BinTreeNode getParent() {
		return parent;
	}
	public void setParent(BinTreeNode parent) {
		this.parent = parent;
	}
	
}
