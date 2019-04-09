package com.joyance.demo.utils;

import java.util.Map;
import java.util.TreeMap;

public class Link {

	Node head;
	
	Node tail;

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}
	
	public void put(Node node){
		if(head == null){
			head = node;
		}else{
			if(head.getValue() > node.getValue()){
				Node temp = head;
				int flag = 0;
				while(temp.hasNext()){
					Node temp1 = temp;
					temp = temp.getNext();
					if(temp.getValue() > node.getValue()){
					    continue;	
					}else{
						temp1.setNext(node);
						node.setNext(temp);
						flag = 1;
						break;
					}
				}
				if(flag == 0){
					temp.setNext(node);
				}
						
			}else{
				Node temp = head;
				head = node;
				head.setNext(temp);
			}
		}
	}
	
	public void list(){
		if(head == null){
			System.out.println("{}");
		}else{
			StringBuilder str = new StringBuilder("{[");
			str.append(head.getKey()+","+head.getValue());
			str.append("]");
			Node temp = head;
			while(temp.hasNext()){
				temp = temp.getNext();
				str.append("["+temp.getKey()+","+temp.getValue()+"]");
			}
			str.append("}");
			System.out.println(str);
		}
	}

	public static void main(String[] args) {
		Link link = new Link();
		Node node1 = new Node();
		node1.setKey("a");
		node1.setValue(1);
		link.put(node1);
		
		Node node2 = new Node();
		node2.setKey("b");
		node2.setValue(3);
		link.put(node2);
		
		Node node3 = new Node();
		node3.setKey("c");
		node3.setValue(2);
		link.put(node3);
		
		Node node4 = new Node();
		node4.setKey("d");
		node4.setValue(2);
		link.put(node4);
		
		link.list();
		
//		Link link = new Link();
//		String x = "key value key value key abc";
//		String[] array = x.split(" ");
//		Map<String,Integer> map = new TreeMap<String,Integer>();
//		for(String str:array){
//			if(map.containsKey(str)){
//				int count = map.get(str);
//				map.put(str, count+1);
//			}else{
//				map.put(str, 1);
//			}
//		}
//		
//		for(String key:map.keySet()){
//			Node node = new Node();
//			node.setKey(key);
//			node.setValue(map.get(key));
//			link.put(node);
//		}
//		link.list();
	}
}
