package interest;
//背包问题，双重约束
//中兴笔试题
/*
		问题描述
获得反应堆最大能量
		输入描述
reactorCap 		   反应堆容量
numbeOfRadLiquid 现有小瓶数量		1——10^4
criticalMass     反应堆最大临界质量
volumes			   列表，N份放射性液体的体积
messes			   列表，N份放射性液体的质量
energies		   列表，N份放射性液体产生的能量		

		输出描述
最大能量
		样例输入
100							反应堆容量(V)
5							现有小瓶数量(N)
15							反应堆最大临界质量(M)
{50,40,30,20,10}			N份放射性液体的体积
{1,2,3,9,5}					N份放射性液体的质量
{300,480,270,200,180}		N份放射性液体产生的能量		
		样例输出
960							最大能量
*/

public class Knapsack_problem2 {
	public static void main(String[] args) {
		int v =100;		//约束一：反应堆容量
		int m =15;		//约束三：反应堆最大临界质量
		int n =5;		//现有小瓶数量,即可使用的放射性液体数量，参数对应下面的整数列表
		int[] volume = {50,40,30,20,10};
		int[] weight = {1,2,3,9,5};
		int[] value = {300,480,270,200,180};
		solution(v,m,n,volume,weight,value);
	}
	private static void solution(int v, int m, int n, int[] volume, int[] weight, int[] value) {
		// TODO Auto-generated method stub
		int[][][] res = new int [n+1][v+1][m+1];
		for(int i = 1;i<=n;i++) {		//所有小瓶数量
			for(int j =1;j<=v;j++) {	//体积
				for(int k=1;k<=m;k++) {	//质量
					if(volume[i-1]>j || weight[i-1]>k) {
						res[i][j][k]=res[i-1][j][k];
					}else {
						res[i][j][k]=max(res[i-1][j][k],res[i-1][j-volume[i-1]][k-weight[i-1]]+value[i-1]);
					}
				}
			}
		}
		System.out.println(res[n][v][m]);
	}

	private static int max(int i, int j) {
		// TODO Auto-generated method stub
		if(i>j) {
			return i;
		}
		return j;
	}
}
