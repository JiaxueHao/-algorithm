package interest;

import java.io.*;
import java.util.*;

public class StringUtil {
	//���붼��Сд��ĸ
	public static boolean isLowerCase(String name) {
		int lowercase=0;
		for(int i = 0;i < name.length();i++){
			char ch = name.charAt(i);
			//���붼Ϊ�ַ�
			if(Character.isLetter(ch) && Character.isLowerCase(ch)){
					lowercase++; 
			}
		}
		if(lowercase==name.length()) {
			return true;
		}else {
			return false;
		}
	}
	
	/* ����һ���������������������������ʽ�������

	���򲻿��Ǹ���������������ֺ���0����������ʽҲ����0��������Ϊ100�������Ϊ001 */
	
    public static Integer reverseInt(String s){
        StringBuilder sb = new StringBuilder(s);
        StringBuilder r = sb.reverse();
        String v = r.toString();
        Integer in= Integer.valueOf(v);
        return in;
    }
    
    private static String reverseInt2(int n){
        String str = String.valueOf(n);
        StringBuffer sb = new StringBuffer();
        for(int i=str.length()-1;i>=0;i--){
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
	
    
    //�����ַ����к��еĲ�ͬ�ַ��ĸ������ַ���ACSII�뷶Χ��(0~127)�����ڷ�Χ�ڵĲ���ͳ�ơ�
    //�����ַ���ACSII�뷶Χ��(0~127)
    public static int  getDiffSum(String s) {
    	int len =0;
    	Set<Integer> l = new HashSet<>();
    	for (int i = 0;i<s.length();i++) {
    		Character c = s.charAt(i);
    		int a = Integer.valueOf(c);
    		if(a>=0 && a<128) {
    			l.add(a);
    		}
    	}
        return l.size();
    }
    
    private static int getDiffSum2(String s){
        List<Character> list = new ArrayList<>();
        for(char ch : s.toCharArray()){
            if(list.contains(ch)){
                 
            }else{
                list.add(ch);
            }
        }
        return list.size();
    }


/*    ��һ��Ӣ������Ե���Ϊ��λ�����ŷš����硰I am a boy���������ŷź�Ϊ��boy a am I��
    ���е���֮����һ���ո����������г���Ӣ����ĸ�⣬���ٰ��������ַ�*/
    
    public static String  reverseSentence(String sentence) {
		StringTokenizer st =new StringTokenizer(sentence);
		ArrayList<String> al = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		
		while(st.hasMoreTokens()) {
			al.add(st.nextToken()+" ");
		}
		for (int i = al.size()-1;i>=0;i--) {
			sb.append(al.get(i));
		}
		return sb.toString();
	
    }
    public static void  reverseSentence2(String str) {
	    String[] strs = str.split(" ");
	    for(int i = strs.length -1; i>=0; i--){
	        System.out.print(strs[i]);
	        if(i != 0)
	        System.out.print(" ");
	    }
    }
/*   ����n�ַ��������n���ַ��������ֵ������С�
    �����һ��Ϊһ��������n(1��n��1000),����n��Ϊn���ַ���(�ַ������ȡ�100),�ַ�����ֻ���д�Сд��ĸ��*/
    public static void  StringSort(String str)  {
    	
    	String[] strs = str.split(" ");
    	Arrays.sort(strs);
    	for(int j=0;j<strs.length;j++){
          System.out.println(strs[j]);
      }
    }
    
    
	public static void main(String[] args) {
		String s1 = "aa  ss  dd f f  g g ss w  w";
		byte[] b = s1.getBytes();
		for(int j=0;j<b.length;j++){
	          System.out.print(b[j]+"  ");
	      }
		StringUtil.StringSort(s1);
		
		
		System.out.println(Character.isLetter('@'));
		String s = " otTfFsSent";
		System.out.println("1:"+s.indexOf("tTf"));//�����򷵻ص�һ�γ��ֵ�λ�ã��������򷵻�-1��
		
		System.out.println("2:"+s.lastIndexOf("t"));//�����򷵻������ֵ�λ�ã��������򷵻�-1��
		System.out.println("3:"+s.charAt(0));//��ȡָ���������ַ�
		System.out.println("4:"+s.trim().charAt(0));//ȥ���ո�
		
		System.out.println("5:"+s.replace("s","67"));//��str�е�����s1�滻��s2
		
		System.out.println("6:"+s.substring(5,9));//���ַ�������5���ַ�����8��
		
		System.out.println("7:"+s.toUpperCase());//ת��Ϊ��д ;  toLowerCase()ת��ΪСд
		
		System.out.println("8:"+String.valueOf(true));//ת��Ϊ�ַ���
		
		System.out.println("9:"+"true".startsWith("t"));//�Ƿ����ض��ַ�����ʼ ;  endWith()���������Ƿ����ض��ַ�������
		System.out.println("10:"+"true".startsWith("u", 2));//"true"�ĵ�2λ�ַ��Ƿ�Ϊu��ͷ
		
		
		StringBuilder sb = new StringBuilder("test");
		sb.append(" ok");
		System.out.println("11:"+sb);
		System.out.println("12:"+sb.reverse());
		System.out.println("13:"+sb);
		System.out.println("14:"+sb.replace(0, 4, "oline"));//sb��0-3���ַ��滻
		
		StringTokenizer st =new StringTokenizer("Welcome to my school");
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	
	}
	
	
	
}
