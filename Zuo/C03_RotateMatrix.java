package Zuo;
//给定一个[整型正方形]矩阵matrix，请把该矩阵调整成 顺时针旋转90度的样子。 
//【要求】 额外空间复杂度为O(1)。

public class C03_RotateMatrix {
	public static void main(String[] args) {
		int[][] arr1 ={ { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		rotateArray(arr1);
		printArray(arr1);
	}
	
	private static void printArray(int[][] arr) {
		// TODO Auto-generated method stub
		for(int i = 0 ;i<arr.length;i++) {
			for(int j = 0; j<arr[0].length;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void rotateArray(int[][] arr) {
		int a = 0;
		int b = 0;
		int c = arr.length-1;
		int d = arr[0].length-1;
		while(a<=c && b<=d) {
			rotateOneCircle(arr,a++,b++,c--,d--);
		}
	}

	private static void rotateOneCircle(int[][] arr, int a, int b, int c, int d) {
		// TODO Auto-generated method stub
		int index = d-b;
		//根据坐标扣边界,一个坐标一个坐标换
		for(int i = 0;i<index;i++) {
			int temp = arr[a][b+i];
			arr[a][b+i] = arr[c-i][b];
			arr[c-i][b] = arr[c][d-i];
			arr[c][d-i] = arr[a+i][d];
			arr[a+i][d] = temp;
		}
	}
}
