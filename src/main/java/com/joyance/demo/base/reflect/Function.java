package com.joyance.demo.base.reflect;

public class Function {
	
    public static class ListNode {
		      int val;
		      ListNode next;
		      ListNode() {}
		      ListNode(int val) { this.val = val; }
		      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
	
	public ListNode convert(ListNode l1){
	       if(l1 == null){
	           return null;
	       }
	       ListNode originBefore = l1;
	       ListNode current = l1.next;
	       ListNode originNext = null;
	       while(current!=null){
	          originNext = current.next;
	          current.next = originBefore;
	          if(l1 == originBefore){
	              originBefore.next = null;
	          }
	          originBefore = current;
	          current = originNext;
	       }
          return originBefore;
	    }
	
	public void print(ListNode l1){
		ListNode l = l1;
		while(l!=null){
			System.out.println(l.val);
			l = l.next;
		}
	}

	public static void main(String[] args) {
		Function f = new Function();
		ListNode l1 = new ListNode(3);
		ListNode l2 = new ListNode(4,l1);
		ListNode l3 = new ListNode(2,l2);
		
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(6,l4);
		ListNode l6 = new ListNode(5,l5);
		ListNode l7 = new ListNode(7,l6);
		
		ListNode l = f.convert(l7);
		f.print(l);
	}
}
