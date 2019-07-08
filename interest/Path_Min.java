package interest;
//动态规划
/*
一个矩阵，有的点可以走用0标记，有的点不能走用1标记，只能向下向右走，路径上所有点的数字和为 路径和，求最小的路径和。
1  1  1  1
2  3  5  4
1  7  2  3
1  3  3  4
2  3  4  1
*/
public class Path_Min {
	public static void main(String[] args) {
		int[][] arr = {{1,1,1,1},{2,3,5,4},{1,7,2,3},{1,3,3,4},{2,3,4,1}};
		getPath(arr);
	}

	private static void getPath(int[][] arr) {
		// TODO Auto-generated method stub
		int[][] dp = new int[arr.length][arr[0].length];
		dp[0][0]=arr[0][0];
		for(int i = 0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				if(i-1>=0 && j-1>=0) {
					dp[i][j]= min(dp[i-1][j]+arr[i][j],dp[i][j-1]+arr[i][j]);
				}else if(i-1>=0){
					dp[i][j]=dp[i-1][j]+arr[i][j];
				}else if(j-1>=0){
					dp[i][j]=dp[i][j-1]+arr[i][j];
				}
			}
		}
		System.out.println(dp[arr.length-1][arr[0].length-1]);
	}

	private static int min(int i, int j) {
		if(i<j) {
			return i;
		}
		return j;
	}
}
