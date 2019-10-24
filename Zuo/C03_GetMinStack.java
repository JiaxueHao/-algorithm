package Zuo;

import java.util.*;

//实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返 回栈中最小元素的操作。 
//【要求】 
//1．pop、push、getMin操作的时间复杂度都是O(1)。 
//2．设计的栈类型可以使用现成的栈结构。
public class C03_GetMinStack {
	
	public static void main(String[] args) {
		MinStack s = new MinStack();
		s.push(4);
		s.push(5);
		s.push(8);
		s.push(3);
		s.push(2);
		s.push(1);
		System.out.println(s.getMin());
		s.pop();
		System.out.println(s.getMin());
		s.pop();
		System.out.println(s.getMin());
		s.pop();
		System.out.println(s.getMin());
	}

	
}
class MinStack{
	
	private Stack<Integer> data = new Stack<>();
	private Stack<Integer> smin = new Stack<>();
	
	public void push(int val) {
		if(data.isEmpty()) {
			data.push(val);
			smin.push(val);
		}else {
			data.push(val);
			int min = Math.min(smin.peek(), val);
			smin.push(min);
		}
	}

	public void pop() {
		if(data.isEmpty()){
			throw new RuntimeException("stack is empty");
		}
		data.pop();
		smin.pop();
	}
	
	public int getMin() {
		if(data.isEmpty()){
			throw new RuntimeException("stack is empty");
		}
		return smin.peek();
	}
}