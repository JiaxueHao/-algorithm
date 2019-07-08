package Zuo;

import java.util.Arrays;

//给定一个数组，求如果排序之后，相邻两数的最大差值，要求时 间复杂度O(N)，且要求不能用非基于比较的排序。
public class C02_MaxGap {
//	public static void main(String[] args) {
//		int[] arr = {0,19,29,41,43,49,69,79,89,99};
//		int res = MaxGap(arr);
//		System.out.println(res);
//	}

	private static int MaxGap(int[] arr) {
		// TODO Auto-generated method stub
		int len = arr.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0;i<len;i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		if(min == max) {
			return 0;
		}
		
		int[] maxs = new int[len];
		int[] mins = new int[len];
		boolean[] hasNum = new boolean[len];
		for(int i =0;i<len;i++) {
			int bid = bucket(arr[i],len-1,min,max);
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], arr[i]) : arr[i];
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], arr[i]) : arr[i];
			hasNum[bid] = true;
		}
		int res = 0;
		int lastMax = maxs[0];
		for(int i = 1;i<len;i++) {
			if(hasNum[i]) {
				res = (mins[i]-lastMax)>res?mins[i]-lastMax:res;
				lastMax = maxs[i];
			}
		}
		return res;
	}

	private static int bucket(int num,int len, int min, int max) {
		// TODO Auto-generated method stub
		int bid = (int) ((num - min) * len / (max - min));
		return bid;
	}
	
	// for test
	public static int comparator(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		Arrays.sort(nums);
		int gap = Integer.MIN_VALUE;
		for (int i = 1; i < nums.length; i++) {
			gap = Math.max(nums[i] - nums[i - 1], gap);
		}
		return gap;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
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

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (MaxGap(arr1) != comparator(arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
