package Zuo;

import java.util.*;

//�ж�һ�������Ƿ�Ϊ���Ľṹ 
//����Ŀ�� ����һ�������ͷ�ڵ�head�����жϸ������Ƿ�Ϊ�� �Ľṹ�� 
//���磺
	//1->2->1������true��
	//1->2->2->1������true�� 
	//15->6->15������true�� 1->2->3������false�� 
//����(���Ž�)�� ���������ΪN��ʱ�临�ӶȴﵽO(N)������ռ临�� �ȴﵽO(1)��
//���ԣ������ǿռ临�Ӷȣ����˼���
//���ԣ�Ҫ���ǿռ临�Ӷ�
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
	//�ռ临�Ӷ� O(n) ѹջ
	public static boolean IsPalindromeList1(Node head) {
		Stack<Integer> s = new Stack<>();
		Node cur = head;
		//ѹջ
		while(cur!=null) {
			s.push(cur.val);
			cur=cur.next;
		}
		//�Ƚ�
		while(!s.isEmpty()) {
			if(s.pop()==head.val) {
				head = head.next;
			}else {
				return false;
			}
		}                    
		return true;
	}
	//�ռ临�Ӷ� O(n/2) ����ָ��+ѹջ
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
	//�ռ临�Ӷ� O(1) ����ָ��+��������+�ָ�����˳��
	public static boolean IsPalindromeList3(Node head) {
		boolean res = true;
		Node fast = head;
		Node slow = head;
		Node mid = null;
		//����ָ��
		while(fast.next!=null && fast.next.next!=null) {
			fast = fast.next.next;
			slow = slow.next;
			mid = slow;
		}
		//��ת����
		Node pre =mid;
		slow = slow.next;
		while(slow!=null) {
			Node next = slow.next;
			slow.next = pre;
			pre = slow;
			slow = next;
		}
		//�Ƚ�
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
		//�ָ�����
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
