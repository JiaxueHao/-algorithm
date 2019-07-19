package Zuo;
//��֮�����δ�ӡ���� ����Ŀ�� ����һ������matrix�����ա�֮�����εķ�ʽ��ӡ�� ������
//���磺
//1 2  3  4 
//5 6  7  8 
//9 10 11 12 
//��֮�����δ�ӡ�Ľ��Ϊ��
//1��2��5��9��6��3��4��7��10��11�� 8��12 
//��Ҫ�� ����ռ临�Ӷ�ΪO(1)��
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
			//ע��˳�����ж�������==�����󣬲ſ��Խ��������+1
			ar = ac==endc ? ar+1:ar;
			ac = ac==endc ? ac:ac+1;
			bc = br==endr ? bc+1:bc;
			br = br==endr ? br:br+1;
			//һ��б���ϴ�ӡ��һ��б���´�ӡ
			toup=!toup;
		}
		
	}

	private static void printZig(int[][] arr, int ar, int ac, int br, int bc, boolean toup) {
		// TODO Auto-generated method stub
		if(toup) {
			//���µ��ϴ�ӡ
			while(br!=ar-1) {
				System.out.print(arr[br--][bc++]+" ");
			}
		}else {
			//���ϵ��´�ӡ
			while(ar!=br+1) {
				System.out.print(arr[ar++][ac--]+" ");
			}
		}
	}

}
