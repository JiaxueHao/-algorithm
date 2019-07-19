package Zuo;
//如何仅用队列结构实现栈结构
//如何仅用栈结构实现队列结构
	//s1的数据倒到s2中
	//要倒数据就全倒
	//等s2的数据都用完后，再从s1倒
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
	//s1空        s2空		异常
	//s1不空    s2不空	return s2.pop();
	//s1空 	s2不空	return s2.pop();
	//s1不空  	s2空		倒数据
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
	//始终从q2中pop
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
	
