package com.joyance.demo.base.sort;

public class LinkedList1 {
	
	private Node head;

	private Node tail;
	
	private int length;
	
	public static class Node{
		public String value;
		public Node next;
		public Node(String value){
			this.value = value;
		}
	}
	
	public void append(String value){
		Node node = new Node(value);
		if(isEmpty()){
			head = node;
		}else{
			tail.next = node;
		}
		tail = node;
		length++;
	}
	
	public void print(){
		Node node = head;
		while(node!=null){
			System.out.println(node.value);
			node = node.next;
		}
	}
	
	public void insert(int index,String value){
		if(index < 0 || index > length){
			throw new RuntimeException("insert错误的index");
		}
		Node node = new Node(value);
		if(index == 0){
			node.next = head;
			head = node;
		}else{
			int cursor = 0;
			Node curNode = head;
			Node preNode = head;
			while(cursor++<index){
				preNode = curNode;
				curNode = curNode.next;
			}
			preNode.next = node;
			node.next = curNode;
		}
		length++;
	}
	
	public boolean isEmpty(){
		return length == 0;
	}
	
	public Node head(){
		return head;
	}
	
	public Node tail(){
		return tail;
	}
	
	public int length(){
		return length;
	}
	
	public Node remove(int index){
		if(index < 0 || index > length){
			throw new RuntimeException("remove错误的index");
		}
		Node delNode = null;
		if(index == 0){
			delNode = head;
			head = head.next;
		}else{
			int c = 0;
			Node curNode = head;
			Node preNode = null;
			while(c++ < index){
				preNode = curNode;
				curNode = curNode.next;
			}
			delNode = curNode;
			preNode.next = curNode.next;
			
			if(delNode == tail){
				tail = preNode;
			}
		}
		length--;
		return delNode;
	}
	
	/**
	 * 链表反转
	 */
	public static void reverse(LinkedList1 ll){
	    Node leftNode = ll.head;
	    Node rightNode = ll.head.next;
	    
	    Node newTail = null;
	    
	    int index = 0;
	    while(rightNode!=null){
	    	if(index == 0){
	    		newTail = leftNode;
	    	}
	    	Node rrNode= rightNode.next;
	    	rightNode.next = leftNode;
	    	leftNode = rightNode;
	    	rightNode = rrNode;
	    	index++;
	    }
	    ll.head = leftNode;
	    ll.tail = newTail;
	    ll.tail.next = null;
	    ll.print();
	}
	
	public static void main(String[] args) {
		LinkedList1 l = new LinkedList1();
		l.append("1");
		l.append("2");
		l.append("3");
		
		l.insert(2, "5");
//		l.remove(3);
//		l.print();
//		System.out.println("head is "+l.head.value +" tail is "+l.tail.value);
//		System.out.println("length is "+l.length);
		
		LinkedList1.reverse(l);
	}
}
