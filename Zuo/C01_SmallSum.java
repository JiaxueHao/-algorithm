package Zuo;
/*小和
比右边数小的数字的和
输入  {1，5，2，4}
输出   1+1+1+2=5
*/
public class C01_SmallSum {
	
	public static void main(String[] args) {
		int testTime = 50000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			int a = smallsum(arr1);
			int b = comparator(arr2);
			if (a!=b) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
	
	public static int smallsum(int[] arr) {
		return mergesort(arr,0,arr.length-1);	
	}
	
	public static int mergesort(int[] arr,int left,int right) {
		if(left<right) {
			int middle=left+(right-left)/2;
			return mergesort(arr,left,middle)+	
			mergesort(arr,middle+1,right)+
			merge(arr,left,middle,right);
		}
		else {
			return 0;
		}
	}
	
	public static int merge(int[] arr,int left,int middle,int right) {
		int l=left;
		int r=right;
		int m=middle+1;
		int sum = 0;
		int[] temp=new int[r-l+1];
		int j=0;
		while(l<=middle && m<=r) {
			if(arr[l]<arr[m]) {
				temp[j]=arr[l];
				sum = sum + arr[l]*(right-m+1);//小和计算
				j++;
				l++;
			}else {
				temp[j]=arr[m];
				j++;
				m++;
			}	
		}
		while(m<=r) {
			temp[j++]=arr[m++];
		}
		while(l<=middle) {
			temp[j++]=arr[l++];
		}
		for(int k =left;k<=right;k++) {
			arr[k]=temp[k-left];
		}
		return sum;
	}

	
	// for test
	public static int comparator(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int res = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				res += arr[j] < arr[i] ? arr[j] : 0;
			}
		}
		return res;
	}
	
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
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}





















