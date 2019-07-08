package interest;
//背包问题，双重约束

/*
		问题描述
小豆手中有n个金币，但身上最多穿着m件装备，每件装备的对应的价格x金币，对应的装备等级是y。
现在小豆想要用手中的金币买到装备等级最大的装备组合。问小豆能买到最大的装备等级。
		输入描述
金币数量n
最多穿装备的数量m
价格x，装备等级y
		输出描述
能买到的装备等级加和
		样例输入
130				金币数量n
3				最多穿装备的数量m
100 380			价格x，装备等级y
20 320
40 360
50 310
		样例输出
990				能买到的装备等级加和
*/

public class Knapsack_problem1 {
	public static void main(String[] args) {
		int n =130;		//约束一：拥有的金币数量
		int k =3;		//约束二：最多的装备数量
		int m =4;		//所有装备数量
		int[] value = {0,100,20,40,50};
		int[] grade = {0,380,320,360,310};
		solution(n,m,k,value,grade);
	}
	private static void solution(int n, int m,int k, int[] value, int[] grade) {
		// TODO Auto-generated method stub
		int[][][] res = new int[m+1][n+1][k+1];
		for(int l = 1;l<=k ;l++) {			//装备数量约束
			for(int j =1;j<=n;j++) {		//金币约束
				for(int i =1;i<=m;i++) {	//所有装备
					if(value[i]>j) {
						res[i][j][l] = res[i-1][j][l];
					}else {
						res[i][j][l] = max(res[i-1][j][l],res[i-1][j-value[i]][l-1]+grade[i]);
					}
				}
			}
		}
		
		System.out.println(res[m][n][k]);
	}

	private static int max(int i, int j) {
		// TODO Auto-generated method stub
		if(i>j) {
			return i;
		}
		return j;
	}
}
