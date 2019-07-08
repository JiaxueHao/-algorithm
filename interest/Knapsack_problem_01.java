package interest;
//01状态，背包问题

import java.util.Scanner;

/*
	①、将原问题分解为⼦问题（子问题和原问题形式相同，且子问题解求出就会被保存）；
	②、确定状态：01背包中⼀个状态就是N个物体中第i个是否放入体积为V 背包中；
	③、确定⼀些初始状态（边界状态）的值； 
	④、确定状态转移⽅程，如何从⼀个或多个已知状态求出另⼀个未知状态的值。（递推型）
	
	共5件物品
	背包体积为12
	物品价值分别为  { 2 , 5 , 3 , 10 , 4}
	物体体积为  { 1 , 3 , 2 , 6 , 2}
	
	输出结果：21
*/

public class Knapsack_problem_01 {
	public static void main(String[] args) {
		int n =5;
		int m =12;
		int[] value = {0,2,5,3,10,4};
		int[] volume = {0,1,3,2,6,2};
		solution(n,m,value,volume);
		
	}

	private static void solution(int n, int m, int[] value, int[] volume) {
		// TODO Auto-generated method stub
		int[][] res = new int[n+1][m+1];
		for(int i =1;i<=n;i++) {
			for(int j =1;j<=m;j++) {
				if(volume[i]>j) {
					res[i][j] = res[i-1][j];
				}else {
					res[i][j] = max(res[i-1][j],res[i-1][j-volume[i]]+value[i]);
				}
			}
		}
		System.out.println(res[n][m]);
	}

	private static int max(int i, int j) {
		// TODO Auto-generated method stub
		if(i>j) {
			return i;
		}
		return j;
	}
}
