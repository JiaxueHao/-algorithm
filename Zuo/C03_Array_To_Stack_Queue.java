package Zuo;
//用数组结构实现大小固定的队列和栈

public class C03_Array_To_Stack_Queue {
	
	public static void main(String [] args) {
		ArrayStack s = new ArrayStack(5);
		s.push(0);
		s.pop();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.pop();
		s.push(5);
		s.printArray();
		s.push(6);
		s.printArray();
//		s.push(6);
		
		ArrayQueue q = new ArrayQueue(3);
		q.add(1);
		q.add(2);
		q.add(3);
		q.printArray();
		q.remove();
		q.add(4);
		q.printArray();
		q.remove();
		q.add(5);
		q.printArray();
	}
	
}
class ArrayStack{
	
	int size = 0;
	int[] stack;
	
	public ArrayStack(int len) {
		if(len<0) {
			throw new IllegalArgumentException("The init size is less than 0");
		}
		stack = new int[len];
	}
	
	public void push(int value) {
		if(size == stack.length) {
			throw new ArrayIndexOutOfBoundsException("stack is full");
		}
		stack[size++]=value;
	}
	
	public int pop() {
		if(size == 0) {
			throw new ArrayIndexOutOfBoundsException("stack is empty");
		}
		return stack[--size];
	}
	public void printArray(){
		for(int i =0; i<size;i++) {
			System.out.print(stack[i]+" ");
		}
		System.out.println();
	}
}

class ArrayQueue{
	
	int[] queue;
	int size = 0;
	int start = 0;
	int end = 0;
	
	public ArrayQueue(int len) {
		if(len<0) {
			throw new IllegalArgumentException("The init size is less than 0");
		}
		queue = new int[len];
	}
	
	public void printArray(){
		for(int i =0; i<size;i++) {
			System.out.print(queue[i]+" ");
		}
		System.out.println();
	}
	
	public void add(int value) {
		if(size == queue.length) {
			throw new ArrayIndexOutOfBoundsException("queue is full");
		}
		//有界队列，队列满时，添加到列头
		int index = (end++) % queue.length;
		queue[index]=value;
		size++;
	}
	public int remove() {
		if(size == 0) {
			throw new ArrayIndexOutOfBoundsException("queue is empty");
		}
		int index = (start++) % queue.length;
		size--;
		return queue[index];
	}
}