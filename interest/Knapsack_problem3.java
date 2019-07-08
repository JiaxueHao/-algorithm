package interest;
//背包问题，完全背包，区别其他背包问题，一个物体可以放无限次

/*
		问题描述
获得反应堆最大能量
		输入描述
reactorCap 		   反应堆容量
numbeOfRadLiquid 现有小瓶数量		1——10^4
volumes			   列表，N份放射性液体的体积
energies		   列表，N份放射性液体产生的能量		

		输出描述
最大能量
		样例输入
10							反应堆容量(V)
6							现有小瓶数量(N)
{3,2,5,1,6,4}				N份放射性液体的体积
{6,5,10,2,16,8}				N份放射性液体产生的能量		
		样例输出
960							最大能量
*/

public class Knapsack_problem3 {
	public static void main(String[] args) {
		int v =10;		//约束一：反应堆容量
		int n =4;		//现有小瓶数量,即可使用的放射性液体数量，参数对应下面的整数列表
		int[] volume = {2,3,4,7};
		int[] value = {1,3,5,9};
		solution(v,n,volume,value);
	}
	private static void solution(int v, int n, int[] volume, int[] value) {
		// TODO Auto-generated method stub
		int[][] res = new int [n+1][v+1];
		for(int i =1;i<=n;i++) {		//所有反应物
			for(int j =1; j<=v;j++) {	//反应堆容量
				if(volume[i-1]>j) {
					res[i][j] = res[i-1][j];
				}else {
					for(int k=0;k*volume[i-1]<=j;k++) {
						res[i][j] = max(res[i-1][j],					//不放
								res[i][j-k*volume[i-1]]+k*value[i-1]);	//放:k=0 res[i]表示已经有一个i物品放进去了	
					}
				}
			}
		}
		System.out.println(res[n][v]);
	}

	private static int max(int i, int j) {
		// TODO Auto-generated method stub
		if(i>j) {
			return i;
		}
		return j;
	}
}
