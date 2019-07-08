package Zuo;

public class C02_NetherlandsFlag {
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] test = generateArray();

		printArray(test);
		int[] res = partition(test, 0, test.length - 1, 1);
		printArray(test);
		System.out.println(res[0]);
		System.out.println(res[1]);

	}
	
	private static int[] partition(int[] test, int i, int j, int k) {
		// TODO Auto-generated method stub
		int l = i-1;
		int r = j+1;
		int cur = i;
		while(cur<r) {
			if(test[cur]<k) {
				swap(test,++l,cur++);
			}else if(test[cur]>k){
				swap(test,--r,cur);
			}else {
				cur++;
			}
		}
		return new int[] {l+1,r-1};
	}

	private static void swap(int[] test, int i, int j) {
		// TODO Auto-generated method stub
		int temp = test[i];
		test[i]=test[j];
		test[j]=temp;
	}

	public static int[] generateArray() {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 4);
		}
		return arr;
	}
}
