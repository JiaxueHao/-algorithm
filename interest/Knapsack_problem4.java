package interest;
//背包问题，多重背包，区别其他背包问题，一个物体可以放k次

/*
		问题描述
用有限的资金最多能采购多少公斤粮食呢
		输入描述
money 		   钱
kind 		   大米种类
price        大米价格
weight		   大米重量
num			   大米袋数

		输出描述
最大能量
		样例输入
8							钱
2							大米种类
2 100 4						价格 重量 袋数
4 100 2							
		样例输出
400							最大能量
*/

public class Knapsack_problem4 {
	public static void main(String[] args) {
		int money = 8;		//约束一：钱
		int kind = 2;		//大米种类
		int[] price = {2,4};
		int[] weight = {100,100};
		int[] num = {4,2};
		solution(money,kind,price,weight,num);
	}
	private static void solution(int money, int kind, int[] price, int[] weight, int[] num) {
		// TODO Auto-generated method stub
		int[][] res = new int[kind+1][money+1];
		for(int i =1;i<=kind;i++) {
			for(int j =1; j <=money;j++) {
				if(price[i-1]>j) {
					res[i][j]= res[i-1][j];
				}else {
					for(int k = 0;k<=num[i-1] && k*price[i-1]<= j ;k++) {
						int  max = max(res[i-1][j],						//不放
								res[i][j-k*price[i-1]]+k*weight[i-1]);	//放，k=0 res[i]表示已经有一个i物品放进去了		
						res[i][j] = max;
					}
				}
			}
		}
		System.out.println(res[kind][money]);
	}

	private static int max(int i, int j) {
		// TODO Auto-generated method stub
		if(i>j) {
			return i;
		}
		return j;
	}
}
