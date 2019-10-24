package Zuo;
//��ת�����˫������ ����Ŀ�� �ֱ�ʵ�ַ�ת��������ͷ�ת˫������ĺ����� 
//��Ҫ�� ���������ΪN��ʱ�临�Ӷ�Ҫ��ΪO(N)������ռ� ���Ӷ�Ҫ��ΪO(1)
public class C03_ReverseList {
	
	class Node{
		private int value;
		private Node next;
		
		public Node(int data) {
			this.value = data;
		}
	}
	//��ת��������
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
	//��ת˫������
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
