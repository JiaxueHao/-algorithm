package Zuo;

import java.util.Arrays;

/*  改进的随机快排
            通过荷兰国旗问题改进  经典排序每次只排一个数字
            	  随机快排返回一个数组  其中为等于pivot的所有数（排好与pivot相等的所有数字）
	随机取数字  而不是简单的通过数组首位定位 pivot
	*/
public class C02_QuickSort {
	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}
	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
	
	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	public static void main(String args[]) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			quickSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(10, 10);
		printArray(arr);
		quickSort(arr);
		printArray(arr);
	}
	
	public static void quickSort(int[] arr) {
		quicksort(arr,0,arr.length-1);
	}

	public static void quicksort(int[] arr,int left,int right) {
		if(left<right) {
			swap(arr,left,(int)(Math.random()*(right-left+1))+left);	//实现随机，打乱数组次序
			int[] middle=partition(arr,left,right);
			quicksort(arr,left,middle[0]-1);
			quicksort(arr,middle[1]+1,right);
		}
		
	}	
	public static int[] partition(int[] arr,int left,int right) {
		int l = left-1;
		int r = right+1;
		int cur = left;
		int pivot = arr[left];
		while(cur<r) {
			if(arr[cur]<pivot) {
				swap(arr,++l,cur);
				cur++;
			}else if (arr[cur]>pivot) {
				swap(arr,--r,cur);
			}else {
				cur++;
			}
		}
		return new int[] {++l,--r};
	}
	
	public static void swap(int[] arr,int i,int j )
	{
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}	
}
