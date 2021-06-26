package com.joyance.demo.base.sort;

import com.alibaba.fastjson.JSON;

/**
 * 哈夫曼树，用堆实现
 */
public class Huffman {

	public static class Node{
		public String value;
		public double ratio;
	}
	
	public static class Tree{
		public Node node;
		public Tree parent;
		public Tree left;
		public Tree right;
	}
	
	public static class Heap{
		
		public Node[] array = new Node[100];
		public int size = 0;
		
		public Node[] insert(Node node){
			array[size++] = node;
			
			int index = size - 1;
			while(index > 0){
				int parent = (index-1)/2;
				if(array[parent].ratio > node.ratio){
					Node temp = array[parent];
					array[parent] = node;
					array[index] = temp;
				}
				index = parent;
			}
			return array;
		}
		
		public void print(){
			for(int i=0;i<size;i++){
				System.out.print(JSON.toJSONString(array[i])+" ");
				if(i == size - 1){
					System.out.println();
				}
			}
			
		}
		
		public void shift(){
			for(int i = 0;i <= (size - 1)/2;i++){
				int childIndex = 2*i+1;
				int parentIndex = i;
				while(childIndex < size){
					Node minNode = null;
					int minIndex = -1;
					if(childIndex + 1 < size && array[childIndex + 1].ratio < array[childIndex].ratio){
						minNode = array[childIndex + 1];
						minIndex = childIndex + 1;
					}else{
						minNode = array[childIndex];
						minIndex = childIndex;
					}
					if(array[parentIndex].ratio > minNode.ratio){
						array[minIndex] = array[parentIndex];
						array[parentIndex] = minNode;
						
						parentIndex = 0;
						childIndex = 1;
					}else{
						parentIndex = childIndex;
						childIndex = 2 * childIndex + 1;
					}
				}
			}
		}
		
		public Node removeMin(){
			Node remove = array[0];
			array[0] = array[--size];
			shift();
			return remove;
		}
	}
	
	//huffman编码
	public void huffman(Node[] nodes){
		Heap heap =new Heap();
		for(Node node:nodes){
			heap.insert(node);
		}
		
		Tree treeLeft = new Tree();
		treeLeft.node = heap.removeMin();
		Tree treeRight = new Tree();
		treeRight.node = heap.removeMin();
		Tree parentTree = new Tree();
		parentTree.left = treeLeft;
		parentTree.right = treeRight;
		
		while(heap.size != 0){
			Tree newTree = new Tree();
			newTree.right = parentTree;
			newTree.left = new Tree();
			newTree.left.node = heap.removeMin();
			parentTree = newTree;
		}
		
		String str = "";
		while(parentTree!=null){
			Tree left = parentTree.left;
			Tree right = parentTree.right;
			if(left!=null){
				String v = str + "0";
				System.out.println(left.node.value+" "+v);
			}
			if(right!=null && right.node!=null){
				String v = str + "1";
				System.out.println(right.node.value+" "+v);
			}
			parentTree = right;
			str+="1";
		}
		
	}
	
	public static void main(String[] args) {
		Heap heap =new Heap();
		Node[] nodes = new Node[5];
		Node node1 = new Node();
		node1.ratio = 0.12;
		node1.value = "a";
		
		Node node2 = new Node();
		node2.ratio = 0.4;
		node2.value = "b";
		
		Node node3 = new Node();
		node3.ratio = 0.15;
		node3.value = "c";
		
		Node node4 = new Node();
		node4.ratio = 0.08;
		node4.value = "d";
		
		Node node5 = new Node();
		node5.ratio = 0.25;
		node5.value = "e";
		
		nodes[0] = node1;
		nodes[1] = node2;
		nodes[2] = node3;
		nodes[3] = node4;
		nodes[4] = node5;
		
		heap.insert(node1);
		heap.insert(node2);
		heap.insert(node3);
		heap.insert(node4);
		heap.insert(node5);
		heap.print();
		
//		heap.array[0] = node1;
//		heap.array[1] = node2;
//		heap.array[2] = node3;
//		heap.array[3] = node4;
//		heap.array[4] = node5;
//		heap.size = 5;
		
//		heap.shift();
		heap.removeMin();
		heap.removeMin();
		heap.removeMin();
		heap.removeMin();
		heap.print();
		
		Huffman huffman = new Huffman();
		huffman.huffman(nodes);
	}
}
