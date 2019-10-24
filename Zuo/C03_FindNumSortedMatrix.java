package Zuo;
//�����ж��ź���ľ��������� 
//����Ŀ�� ����һ����N*M�����;���matrix��һ������K�� matrix��ÿһ�к�ÿһ �ж����ź���ġ�
//ʵ��һ���������ж�K �Ƿ���matrix�С� 
//���磺 
	//0 1 2 5 
	//2 3 4 7 
	//4 4 4 8 
	//5 7 7 9 
	//���KΪ7������true�����KΪ6���� ��false�� 
//��Ҫ�� ʱ�临�Ӷ�ΪO(N+M)������ռ临�Ӷ�ΪO(1)��
public class C03_FindNumSortedMatrix {
	
	public static void main(String[] args) {
		int[][] arr = { { 0, 1, 2, 3, 4, 5, 6 },// 0
				{ 10, 12, 13, 15, 16, 17, 18 },// 1
				{ 23, 24, 25, 26, 27, 28, 29 },// 2
				{ 44, 45, 46, 47, 48, 49, 50 },// 3
				{ 65, 66, 67, 68, 69, 70, 71 },// 4
				{ 96, 97, 98, 99, 100, 111, 122 },// 5
				{ 166, 176, 186, 187, 190, 195, 200 },// 6
				{ 233, 243, 321, 341, 356, 370, 380 } // 7
		};
		int[][] arr1 = {{0,1,2,5},{2,3,4,7},{4,4,4,8},{5,7,7,9}};
		int num = 233;
		
		System.out.print("find "+num+" ");
		System.out.print(FindNumSortedMatrix(arr,num));
	}
	
	public static Boolean FindNumSortedMatrix(int[][] arr,int k) {
		int row = arr.length-1;
		int col = arr[0].length-1;
		int i = 0;
		int j = col;
		while(i<=row && j>=0) {
			if(k>arr[i][j]) {
				i++;
			}else if(k<arr[i][j]){
				j--;
			}
			else{
				return true;
			}
		}
		return false;
	}

}
