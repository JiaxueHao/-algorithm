package Zuo;
//使用数组实现堆化(利用堆排序中的堆化，自底向上)，构建大顶对，小顶堆，找中位数
public class C02_FindMedian2 {
	static int[] heapmax = new int[100];
	static int[] heapmin = new int[100];
	private static volatile int len_max =0;
	private static volatile int len_min =0;
	
	public static void main(String[] args) {
		 addNumber(6);
         addNumber(4);
         addNumber(3);
         addNumber(10);
         addNumber(12);
         printArray(heapmax,len_max);
         printArray(heapmin,len_min);
         System.out.println(getMedian());
 
         addNumber(5);
         printArray(heapmax,len_max);
         printArray(heapmin,len_min);
         System.out.println(getMedian());
 
         addNumber(7);
         addNumber(8);
         printArray(heapmax,len_max);
         printArray(heapmin,len_min);
         System.out.println(getMedian());
	}
	
	//始终保持len_max>len_min
	public static void addNumber(int value) {
		if(len_max == len_min) {
			//如果value>maxHeap的最大值，把它插入到minHeap。并且把minHeap的最小值移动到maxHeap
			if(len_max >0 && heapmax[0]<value) {
				add_min(value);
				add_max(heapmin[0]);
				remove_min(heapmin);
			}else {
				//当两个堆都为空时，优先插入大顶堆
				//如果value<=minHeap的最大值，把它插入到maxHeap
				add_max(value);
			}
		}else {
			//如果value <= maxHeap的最大值，把maxHeap的最大值移动到minHeap,并且把value插入到maxHeap。
			if(heapmax[0]>=value) {
				add_max(value);
				add_min(heapmax[0]);
				remove_max(heapmax);
			}else {
				//如果value>maxHeap的最大值，把它插入到minHeap
				add_min(value);
			}
		}
	}
	 public static double getMedian() {
		 if(len_max==0) {
			 return -1;
		 }else {
			 if(len_max == len_min) {
				 return (double)(heapmin[0]+heapmax[0])/2;
			 }else {
				 return heapmax[0];
			 }
		 }
	 }
	private static void remove_min(int[] arr) {
		// TODO Auto-generated method stub
		for(int i = 0; i<len_min;i++) {
			arr[i]=arr[i+1];
		}
		len_min--;
	}

	private static void remove_max(int[] arr) {
		// TODO Auto-generated method stub
		for(int i = 0; i<len_max;i++) {
			arr[i]=arr[i+1];
		}
		len_max--;
	}

	
	public static void add_max(int value) {
		heapmax[len_max]=value;
		len_max++;
		heapify_max(heapmax);
	}
	public static void add_min(int value) {
		heapmin[len_min]=value;
		len_min++;
		heapify_min(heapmin);
	}
	private static void heapify_min(int[] heapmin) {
		for(int j = len_min/2-1;j>=0;j--) {
			int min = 2*j+1;
			if(2*j+2<len_min && heapmin[2*j+2]<heapmin[2*j+1]) {
				min = 2*j+2;
			}
			if(heapmin[min]>=heapmin[j]) {
				min = j;
			}
			swap(heapmin,min,j);
		}
	}
	private static void heapify_max(int[] heapmax) {
		for(int j = len_max/2-1;j>=0;j--) {
			int max = 2*j+1;
			if(2*j+2<len_max && heapmax[2*j+2]>heapmax[2*j+1]) {
				max = 2*j+2;
			}
			if(heapmax[max]<=heapmax[j]) {
				max = j;
			}
			swap(heapmax,max,j);
		}
	}

	private static void swap(int[] arr, int i, int j) {
		// TODO Auto-generated method stub
		int temp = arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}

	public static void printArray(int[] arr,int len) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < len; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
