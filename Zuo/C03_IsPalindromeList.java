package Zuo;

import java.util.*;

//判断一个链表是否为回文结构 
//【题目】 给定一个链表的头节点head，请判断该链表是否为回 文结构。 
//例如：
	//1->2->1，返回true。
	//1->2->2->1，返回true。 
	//15->6->15，返回true。 1->2->3，返回false。 
//进阶(最优解)： 如果链表长度为N，时间复杂度达到O(N)，额外空间复杂 度达到O(1)。
//笔试：不考虑空间复杂度，过了即可
//面试：要考虑空间复杂度
public class C03_IsPalindromeList {
	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(1);
		System.out.println(IsPalindromeList3(head));
		
		
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(2);
		head1.next.next.next = new Node(1);
		System.out.println(IsPalindromeList3(head1));
		
		Node head2 = new Node(1);
		head2.next = new Node(2);
		head2.next.next = new Node(3);
		head2.next.next.next = new Node(1);
		System.out.println(IsPalindromeList3(head2));
//		printList(head);
	}
	
	static class Node{
		int val;
		Node next;
		public Node(int val) {
			this.val = val;
		}
	}
	//空间复杂度 O(n) 压栈
	public static boolean IsPalindromeList1(Node head) {
		Stack<Integer> s = new Stack<>();
		Node cur = head;
		//压栈
		while(cur!=null) {
			s.push(cur.val);
			cur=cur.next;
		}
		//比较
		while(!s.isEmpty()) {
			if(s.pop()==head.val) {
				head = head.next;
			}else {
				return false;
			}
		}                    
		return true;
	}
	//空间复杂度 O(n/2) 快慢指针+压栈
	public static boolean IsPalindromeList2(Node head) {
		Stack<Integer> s = new Stack<>();
		Node fast = head;
		Node slow = head;
		while(fast.next!=null && fast.next.next!=null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		while(slow!=null) {
			s.push(slow.val);
			slow = slow.next;
		}
		while(!s.isEmpty()) {
			if(s.pop()==head.val) {
				head = head.next;
			}else {
				return false;
			}
		}
		return true;
	}
	//空间复杂度 O(1) 快慢指针+链表逆序+恢复链表顺序
	public static boolean IsPalindromeList3(Node head) {
		boolean res = true;
		Node fast = head;
		Node slow = head;
		Node mid = null;
		//快慢指针
		while(fast.next!=null && fast.next.next!=null) {
			fast = fast.next.next;
			slow = slow.next;
			mid = slow;
		}
		//反转链表
		Node pre =mid;
		slow = slow.next;
		while(slow!=null) {
			Node next = slow.next;
			slow.next = pre;
			pre = slow;
			slow = next;
		}
		//比较
		Node cur = pre;
		Node h =head;
		while(pre!=mid) {
			if(pre.val==h.val) {
				pre=pre.next;
				h=h.next;
			}
			else {
				res =  false;
				break;
			}
		}
		//恢复链表
		Node next =null;
		pre = null;
		while(cur!=mid) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur =next;
		}
		return res;
	}
	
	public static void printList(Node head) {
		while(head!=null) {
			System.out.print(head.val+" ");
		}
	}
}
