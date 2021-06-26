package com.joyance.demo.base.sort;

import com.joyance.demo.base.sort.LinkedList1.Node;

public class LinkedList2 {

	/**
	 * 倒序打印
	 * @param node
	 */
	public void reversePrint(Node node){
		if(node==null){
			return;
		}
		reversePrint(node.next);
		System.out.println(node.value);
	}
	
	/**
	 * 打印倒数第n个
	 */
	public void printn(int n,LinkedList1 l1){
		int index = 0;
		Node beginNode = l1.head();
		Node indexNode = l1.head();
		while(beginNode!=null){
			beginNode = beginNode.next;
			if(index++ >= n){
				indexNode = indexNode.next;
			}
		}
		System.out.println(indexNode.value);
	}
	
	/**
	 * 打印链表二分之一的数值
	 * @param l1
	 */
	public void printHalf(LinkedList1 l1){
		Node begin = l1.head();
		Node index = l1.head();
		while(begin!=null && begin.next !=null && (begin=begin.next.next)!=null){
			index = index.next;
		}
		System.out.println(index.value);
	}
	
	/**
	 * 两个有序链表合并
	 */
	public void merge(LinkedList1 l1,LinkedList1 l2){
		if(l1==null && l1==null){
			System.out.println("空链表");
		}
		if(l1==null){
		   l2.print();
		}
		if(l2==null){
		   l1.print();
		}
		Node node1 = l1.head();
		Node node2 = l2.head();
		
		LinkedList1 l3 = new LinkedList1();
		while(node1!=null && node2!=null){
			int v1 = convert(node1.value);
			int v2 = convert(node2.value);
			if(v1 < v2){
				l3.append(v1+"");
				node1 = node1.next;
			}else {
				l3.append(v2+"");
				node2 = node2.next;
			}
		}
		
		if(node1!=null){
			while(node1!=null){
				l3.append(node1.value+"");
				node1 = node1.next;
			}
		}
		
		if(node2!=null){
			while(node2!=null){
				l3.append(node2.value+"");
				node2 = node2.next;
			}
		}
		
		l3.print();
	}
	
	private int convert(String value){
		return Integer.parseInt(value);
	}
	
	public static void main(String[] args) {
		LinkedList1 l = new LinkedList1();
		for(int i=1;i<101;i++){
			l.append(i+"");
		}
		LinkedList2 l2 = new LinkedList2();
		l2.reversePrint(l.head());
		System.out.println("=================");
		
		l2.printn(5, l);
		System.out.println("=================");
		
		l2.printHalf(l);
		System.out.println("=================");
		
		LinkedList1 l11 = new LinkedList1();
		l11.append("1");
		l11.append("4");
		l11.append("6");
		LinkedList1 l12 = new LinkedList1();
		l12.append("2");
		l12.append("3");
		l12.append("5");
		l2.merge(l11, l12);
	}
}
