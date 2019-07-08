package Zuo;
//找逆序对
/*输入{3，4，1，2}
      输出4   {3，1}{3，2}{4，1}{4，2}
*/
public class C01_inverse_sequence {
	
	public static void main(String[] args) {
		int testTime = 50000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize,maxValue);
//			int[] arr1 = {0,8,-5,1,-1,0,-2};
			int[] arr2 = copyArray(arr1);
			int a = inverse_sequence(arr1);
			int b = comparator(arr2);
			if (a!=b) {
				succeed = false;
				printArray(arr1);
				System.out.println(a);
				printArray(arr2);
				System.out.println(b);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
	
	public static int inverse_sequence(int[] arr) {
		return mergesort(arr,0,arr.length-1);	
	}
	
	public static int mergesort(int[] arr,int left,int right) {
		if(left<right) {
			int middle=left+(right-left)/2;
			int a = mergesort(arr,left,middle);
			int b = mergesort(arr,middle+1,right);
			int c = merge(arr,left,middle,right);
			return a+b+c;
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
			if(arr[l]<=arr[m]) {
				temp[j]=arr[l];
				j++;
				l++;
			}else {
				temp[j]=arr[m];
				sum=sum+(middle+1-l);//计算有几个逆序对
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

	
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}
	// for test
	public static int comparator(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int res = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				res += arr[j] > arr[i] ? 1 : 0;
			}
		}
		return res;
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



















