package com.joyance.demo.base.sort;

public class BinTree {
	
	private BinTreeNode root;
	private int deep;
	
	public BinTree(String str){
		ArrayStackT<BinTreeNode> as = new ArrayStackT<BinTreeNode>();
		int k = 0;//1:左 2:右
		BinTreeNode item = null;
		
		for(int i=0;i<str.length();i++){
			String value = String.valueOf(str.charAt(i));
		    if("(".equals(value)){
		    	as.push(item);
		    	k=1;
		    }else if(")".equals(value)){
		    	as.pop();
		    }else if(",".equals(value)){
		    	k=2;
		    }else{
		    	item = new BinTreeNode();
		    	item.setData(value);
		    	if(root == null){
		    		root = item;
		    	}else{
		    		BinTreeNode node= as.top();
		    		if(k==1){
		    			node.setLeftChild(item);
		    			item.setParent(node);
		    		}else if(k==2){
		    			node.setRightChild(item);
		    			item.setParent(node);
		    		}
		    	}
		    }
		}
	}
	
	//前序遍历(根左右)
	public void pre(BinTreeNode node){
		if(node == null){
			return;
		}
		System.out.println(node.getData());
		pre(node.getLeftChild());
		pre(node.getRightChild());
	}
	
	//中序遍历(左根右)
	public void middle(BinTreeNode node){
		if(node == null){
			return;
		}
		middle(node.getLeftChild());
		System.out.println(node.getData());
		middle(node.getRightChild());
	}
	
	//中序遍历(左右根)
	public void after(BinTreeNode node){
		if(node == null){
			return;
		}
		after(node.getLeftChild());
		after(node.getRightChild());
		System.out.println(node.getData());
	}
	
	public BinTreeNode getRoot() {
		return root;
	}

	public void setRoot(BinTreeNode root) {
		this.root = root;
	}

	public int getDeep() {
		return deep;
	}

	public void setDeep(int deep) {
		this.deep = deep;
	}
	
	//判断值是否存在
	public BinTreeNode exist(BinTreeNode v,String value){
		if(v == null || v.getData() == null){
			return null;
		}
		if(v.getData().equals(value)){
			return v;
		}
		BinTreeNode node = exist(v.getLeftChild(),value);
		if(node == null){
			node = exist(v.getRightChild(),value);
		}
        return node;		
	}
	
	//翻转
	public BinTreeNode mirror(BinTreeNode node){
		if(node == null){
			return null;
		}
		BinTreeNode rightChild = node.getRightChild();
		BinTreeNode leftChild = node.getLeftChild();
		node.setLeftChild(rightChild);
		node.setRightChild(leftChild);
		mirror(rightChild);
		mirror(leftChild);
		return node;
	}

	public static void main(String[] args) {
		//A(B(D,E),C(,F))
		BinTree bt = new BinTree("A(B(D,E),C(,F))");
		bt.after(bt.getRoot());
		
		System.out.println("================");
		BinTreeNode node = bt.exist(bt.getRoot(), "B");
		System.out.println(node.getData());
		
		System.out.println("================");
	    bt.mirror(bt.getRoot());
	    bt.after(bt.getRoot());
	}
}
