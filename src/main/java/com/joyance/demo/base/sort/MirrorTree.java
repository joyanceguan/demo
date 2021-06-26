package com.joyance.demo.base.sort;

public class MirrorTree {

	public boolean mirrorTree(BinTreeNode binTreeNode){
		if(binTreeNode == null){
			return false;
		}
		BinTreeNode left = binTreeNode.getLeftChild();
		BinTreeNode right = binTreeNode.getRightChild();
		return mirrorTree(left,right);
	}
	
	public boolean mirrorTree(BinTreeNode left,BinTreeNode right){
		if(left == null && right == null){
			return true;
		}
		if(left == null || right == null){
			return false;
		}
		if(left.getData().equals(right.getData())){
			return mirrorTree(left.getLeftChild(),right.getRightChild()) && mirrorTree(left.getRightChild(),right.getLeftChild());
		}
		return false;
	}
	
	public static void main(String[] args) {
		MirrorTree m = new MirrorTree();
		BinTreeNode root = new BinTreeNode();
		root.setData("x");
		BinTreeNode left = new BinTreeNode();
		left.setData("1");
		
		BinTreeNode leftleft = new BinTreeNode();
		leftleft.setData("2");
		
		BinTreeNode leftright = new BinTreeNode();
		leftright.setData("3");
		
		left.setLeftChild(leftleft);
		left.setRightChild(leftright);
		
		BinTreeNode right = new BinTreeNode();
		right.setData("1");
		
		BinTreeNode rightleft = new BinTreeNode();
		rightleft.setData("3");
		
		BinTreeNode rightright = new BinTreeNode();
		rightright.setData("2");
		
		right.setLeftChild(rightleft);
		right.setRightChild(rightright);
		
		root.setLeftChild(left);
		root.setRightChild(right);
		boolean mirror = m.mirrorTree(root);
		System.out.println(mirror);
	}
	
}
