package Zuo;

import java.util.*;

//一.用队列结构实现栈结构
//二.用栈结构实现队列结构:
//	 倒数据行为：
//		1.pop栈有数据时不能倒
//		2.要倒就都倒完
public class StackAndQueueConvert {
	
	public static void main(String[] args) {
		
	}
	
}
class StackToQueue{
	
	Stack<Integer> s1 = new Stack<>();
	Stack<Integer> s2 = new Stack<>();
	
	public void add(int val) {
		s1.push(val);
	}
	
	public int remove() {
		if(!s2.isEmpty()) {
			return -1;
		}
		while(!s1.isEmpty()) {
			s2.push(s1.pop());
		}
		return s2.pop();
	}
	
}
class QueueToStack{
	
	Queue<Integer> q1 = new LinkedList<>();
	Queue<Integer> q2 = new LinkedList<>();
	
	public void push(int val) {
		q1.add(val);
	}
	
	public int pop() {
		if(q1.isEmpty()) {
			return -1;
		}
		while(q1.size()>1) {
			q2.add(q1.remove());
		}
		return q1.remove();
	}
	
}