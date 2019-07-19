package Zuo;
//给定一个整型矩阵matrix，请按照转圈的方式打印它

public class C03_PrintMatrixSpiralOrder {

	public static void main(String[] args) {
		int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
		int[][] arr1 ={ { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		printArray(arr1);
	}
	
	public static void printArray(int[][] arr) {
		int a = 0;
		int b = 0;
		int c = arr.length-1;
		int d = arr[0].length-1;
		while(a<=c && b<=d) {
			printOneCircle(arr,a++,b++,c--,d--);
		}
	}

	private static void printOneCircle(int[][] arr,int a, int b, int c, int d) {
		// TODO Auto-generated method stub
		//边界
		//只有一行
		if(a==c) {
			for(int curb = b;curb<=d;curb++) {
				System.out.print(arr[c][curb]+" ");
			}
		}
		//只有一列
		else if(b==d) {
			for(int cura = a;cura<=c;cura++) {
				System.out.print(arr[c][cura]+" ");
			}
		}
		
		//矩阵
		else {
			int cura = a;
			int curb = b;
			int curc = c;
			int curd = d;
			while(curb<d) {
				System.out.print(arr[a][curb++]+" ");
			}
			while(cura<c) {
				System.out.print(arr[cura++][d]+" ");
			}
			while(curd>b) {
				System.out.print(arr[c][curd--]+" ");
			}
			while(curc>a) {
				System.out.print(arr[curc--][b]+" ");
			}
			
		}
	}
	
}
