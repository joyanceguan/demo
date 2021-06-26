package com.joyance.demo.base.sort;


public class SearchBinTree {

    public Node root;
	
	public static class Node{
		public Node parent;
		public Node leftChild;
		public Node rightChild;
		public int value;
	}
	
	//中序遍历(左根右)
	public void middle(Node node){
		 if(node == null){
			 return;
		 }
		 middle(node.leftChild);
		 System.out.println(node.value);
		 middle(node.rightChild);
	}
	
	public void insert(Node node,int value){
		if(node == null){
			node = new Node();
			node.value = value;
			root = node;
		}else{
			if(node.value > value){
				if(node.leftChild == null){
					node.leftChild = new Node();
					node.leftChild.value = value;
					node.leftChild.parent = node;
				}else{
					insert(node.leftChild,value);
				}
			}else{
				if(node.rightChild == null){
					node.rightChild = new Node();
					node.rightChild.value = value;
					node.rightChild.parent = node;
				}else{
					insert(node.rightChild,value);
				}
			}
		}
	}
	
	public Node search(Node node,int value){
		if(node == null){
			return null;
		}
		if(node.value == value){
			return node;
		}else if(value < node.value){
			return search(node.leftChild,value);
		}else if(value > node.value){
			return search(node.rightChild,value);
		}
		return null;
	}
	
	public void remove(Node node,int value){
		Node result = search(node,value);
		//没有孩子
		if(result.leftChild == null && result.rightChild == null){
			if(result.parent.leftChild == result){
				result.parent.leftChild = null;
			}else{
				result.parent.rightChild = null;
			}
		}else if(result.leftChild == null && result.rightChild != null){
			//有右孩子无左孩子
			if(result.parent.leftChild == result){
				result.parent.leftChild = result.rightChild;
			}else{
				result.parent.rightChild = result.rightChild;
			}
		}else if(result.leftChild != null && result.rightChild == null){
		    //有左孩子无右孩子
			if(result.parent.leftChild == result){
				result.parent.leftChild = result.leftChild;
			}else{
				result.parent.rightChild = result.leftChild;
			}
		}else{
			//左右孩子都有
			Node first  = result.rightChild;
			while(first.leftChild != null){
				first = first.leftChild;
			}
			int fValue = first.value;
			remove(first,fValue);
			result.value = fValue;
		}
	}
	
	
	public static void main(String[] args) {
		SearchBinTree tree = new SearchBinTree();
		tree.insert(tree.root, 10);
		tree.insert(tree.root, 6);
		tree.insert(tree.root, 3);
		tree.insert(tree.root, 8);
		tree.insert(tree.root, 7);
		tree.insert(tree.root, 9);
		
		tree.middle(tree.root);
		tree.remove(tree.root, 6);
		System.out.println("===========");
		tree.middle(tree.root);
//		Node searchNode = tree.search(tree.root, 11);
//		System.out.println(searchNode);
	}
}
