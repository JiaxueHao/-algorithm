package interest;
//动态规划
/*
一个矩阵，有的点可以走用0标记，有的点不能走用1标记，只能向下向右走，问左上走到右下有多少路径
*/

public class Path_All {
	public static void main(String[] args) {
		int[][] arr = {{1,1,1},{0,1,1},{0,0,1},{1,0,1}};
//		int [][] arr= {{1,1},{1,1}};
 		getPath(arr);
	}

	private static void getPath(int[][] arr) {
		// TODO Auto-generated method stub
		int[][] dp = new int[arr.length][arr[0].length];
		dp[0][0]=arr[0][0];
		
		for(int i = 0;i<arr.length;i++) {			//行
			for(int j = 0;j<arr[0].length;j++) {	//列
				if(arr[i][j]==0)
					continue;
				else {
					if(j-1>=0 && i-1>=0){
						dp[i][j] = dp[i-1][j]+dp[i][j-1];
					}
					else if(i-1>=0) {
						dp[i][j] = dp[i-1][j];
					}else if(j-1>=0) {
						dp[i][j] = dp[i][j-1];
					}
				}
			}
		}
		System.out.println(dp[arr.length-1][arr[0].length-1]);
	}
}
