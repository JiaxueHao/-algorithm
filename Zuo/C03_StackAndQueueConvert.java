package Zuo;
//��ν��ö��нṹʵ��ջ�ṹ
//��ν���ջ�ṹʵ�ֶ��нṹ
	//s1�����ݵ���s2��
	//Ҫ�����ݾ�ȫ��
	//��s2�����ݶ�������ٴ�s1��
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class C03_StackAndQueueConvert {
	
	public static void main(String[] args) {
		StackToQueue sq = new StackToQueue();
		sq.add(0);
		sq.add(1);
		sq.add(2);
		sq.add(3);
		System.out.println(sq.remove());
		sq.add(4);
		System.out.println(sq.remove());
		System.out.println(sq.remove());
		System.out.println(sq.remove());
		System.out.println(sq.remove());
		
		System.out.println("_______________________");
		
		QueueToStack qs = new QueueToStack();
		qs.push(5);
		qs.push(6);
		System.out.println(qs.pop());
		qs.push(7);
		qs.push(8);
		qs.push(9);
		System.out.println(qs.pop());
		System.out.println(qs.pop());
		System.out.println(qs.pop());
		System.out.println(qs.pop());
	}
	
}
class StackToQueue{
	
	Stack<Integer> s1 = new Stack<>();
	Stack<Integer> s2 = new Stack<>();
	
	public void add(int val) {
		s1.push(val);
	}
	//s1��        s2��		�쳣
	//s1����    s2����	return s2.pop();
	//s1�� 	s2����	return s2.pop();
	//s1����  	s2��		������
	public int remove() {
		if(!s2.isEmpty()) {
			return s2.pop();
		}else {
			while(!s1.isEmpty()) {
				s2.push(s1.pop());
			}
			return s2.pop();
		}
	}
}
class QueueToStack{
	
	Queue<Integer> q1 = new LinkedList<>();
	Queue<Integer> q2 = new LinkedList<>();
	Queue<Integer> tmp = new LinkedList<>();
	
	public void push(int val) {
		q1.add(val);
	}
	//ʼ�մ�q2��pop
	public int pop() {
		while(q1.size()>1) {
			q2.add(q1.remove());
		}
		tmp = q1;
		q1 = q2;
		q2 = tmp;
		return q2.remove();
	}
}
	
