package Zuo;
//反转单向和双向链表 【题目】 分别实现反转单向链表和反转双向链表的函数。 
//【要求】 如果链表长度为N，时间复杂度要求为O(N)，额外空间 复杂度要求为O(1)
public class C03_ReverseList {
	
	class Node{
		private int value;
		private Node next;
		
		public Node(int data) {
			this.value = data;
		}
	}
	//反转单向链表
	public static Node reverseNode(Node head) {
		Node pre = null;
		Node next = null;
		while(head!=null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	class DoubleNode{
		private int value;
		private DoubleNode next;
		private DoubleNode pre;
		
		public DoubleNode(int data) {
			this.value = data;
		}
	}
	//反转双向链表
	public static DoubleNode reverseDoubleNode(DoubleNode head) {
			DoubleNode pre = null;
			DoubleNode next = null;
			while(head!=null) {
				next = head.next;
				head.next = pre;
				head.pre = next;
				pre = head;
				head = next;
			}
			return pre;
	}

}
