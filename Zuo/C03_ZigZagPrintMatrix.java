package Zuo;
//“之”字形打印矩阵 【题目】 给定一个矩阵matrix，按照“之”字形的方式打印这 个矩阵，
//例如：
//1 2  3  4 
//5 6  7  8 
//9 10 11 12 
//“之”字形打印的结果为：
//1，2，5，9，6，3，4，7，10，11， 8，12 
//【要求】 额外空间复杂度为O(1)。
public class C03_ZigZagPrintMatrix {
	public static void main(String[] args) {
		int[][] arr= {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		ZigZagPrint(arr);
	}

	private static void ZigZagPrint(int[][] arr) {
		// TODO Auto-generated method stub
		int ar = 0;
		int ac = 0;
		int br = 0;
		int bc = 0;
		int endr = arr.length - 1;
		int endc= arr[0].length - 1;
		boolean toup = true;
		while(bc!=endc+1) {
			printZig(arr,ar,ac,br,bc,toup);
			//注意顺序，先判断完所有==条件后，才可以将这个条件+1
			ar = ac==endc ? ar+1:ar;
			ac = ac==endc ? ac:ac+1;
			bc = br==endr ? bc+1:bc;
			br = br==endr ? br:br+1;
			//一次斜向上打印。一次斜向下打印
			toup=!toup;
		}
		
	}

	private static void printZig(int[][] arr, int ar, int ac, int br, int bc, boolean toup) {
		// TODO Auto-generated method stub
		if(toup) {
			//从下到上打印
			while(br!=ar-1) {
				System.out.print(arr[br--][bc++]+" ");
			}
		}else {
			//从上到下打印
			while(ar!=br+1) {
				System.out.print(arr[ar++][ac--]+" ");
			}
		}
	}

}
