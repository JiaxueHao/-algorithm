package Zuo;

import java.util.*;

//用户可以调用add方法将cat类或dog类的 实例放入队列中； 
//用户可以调用pollAll方法，将队列中所有的实例按照进队列 的先后顺序依次弹出；
//用户可以调用pollDog方法，将队列中dog类的实例按照 进队列的先后顺序依次弹出；
//用户可以调用pollCat方法，将队列中cat类的实 例按照进队列的先后顺序依次弹出； 
//用户可以调用isEmpty方法，检查队列中是 否还有dog或cat的实例； 
//用户可以调用isDogEmpty方法，检查队列中是否有dog 类的实例； 
//用户可以调用isCatEmpty方法，检查队列中是否有cat类的实例。
public class C03_DogCatQueue {
	
	private static Queue<PetEnter> dogq;
	private static Queue<PetEnter> catq;
	private int count;
	public C03_DogCatQueue() {
		dogq = new LinkedList<>();
		catq = new LinkedList<>();
		count = 0;
	}
	public void add(Pet pet) {
		String type = pet.getPetType();
		if(type.equals("cat")) {
			catq.add(new PetEnter(pet,this.count++));
		}else if(type.equals("dog")){
			dogq.add(new PetEnter(pet,this.count++));
		}else {
			System.out.println("error! not dog or cat");
		}
	}
	
	public Pet pollAll() {
		if(!catq.isEmpty() && !dogq.isEmpty()) {
			if(catq.peek().count < dogq.peek().count) {
				return catq.poll().getpet();
			}else {
				return dogq.poll().getpet();
			}
		}else if(!catq.isEmpty()) {
			return catq.poll().getpet();
		}else if(!dogq.isEmpty()) {
			return dogq.poll().getpet();
		}else {
			System.out.println("all empty");
			return null;
		}
	}
	public Pet pollDog() {
		if(!dogq.isEmpty()) {
			return dogq.poll().getpet();
		}else {
			System.out.println("dogq empty");
			return null;
		}
	}
	public Pet pollCat() {
		if(!catq.isEmpty()) {
			return catq.poll().getpet();
		}else {
			System.out.println("catq empty");
			return null;
		}
	}
	public boolean isEmpty() {
		return catq.isEmpty() && dogq.isEmpty();
	}
	public boolean isDogEmpty() {
		return dogq.isEmpty();
	}
	public boolean isCatEmpty() {
		return catq.isEmpty();
	}
		
	public static void main(String[] args) {
		C03_DogCatQueue dc = new C03_DogCatQueue();
		Pet dog1 = new Dog();
		Pet dog2 = new Dog();
		Pet cat1 = new Cat();
		dc.add(dog1);
		dc.add(dog2);
		System.out.println(dc.isCatEmpty());
		System.out.println(dc.isEmpty());
		dc.add(cat1);
		System.out.println(dc.pollAll());
		System.out.println(dc.pollCat());
		System.out.println(dc.pollDog());
	}
	//封装Pet，加入count，用来比较狗队列和猫队列谁先入队
	class PetEnter{
		private Pet pet;
		private int count;
		public PetEnter(Pet pet,int count) {
			this.pet = pet;
			this.count = count;
		}
		public int getcount() {
			return this.count;
		}
		public Pet getpet() {
			return this.pet;
		}
		public String getPetEnterType() {
			return this.pet.getPetType();
		}
	}
	
	public static class Pet { 
		
		private String type; 
	
		public Pet(String type) 
		{ 
			this.type = type;
			} 
		
		public String getPetType() 
		{
			return this.type; 
			} 
		}
		
		public static class Dog extends Pet 
		{ 
			public Dog(){ 
				super("dog"); 
				}
		} 
		
		public static class Cat extends Pet 
		{ 
			public Cat(){ 
				super("cat");
				} 
		}
}
