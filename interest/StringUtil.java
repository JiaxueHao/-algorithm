package interest;

import java.io.*;
import java.util.*;

public class StringUtil {
	//输入都是小写字母
	public static boolean isLowerCase(String name) {
		int lowercase=0;
		for(int i = 0;i < name.length();i++){
			char ch = name.charAt(i);
			//输入都为字符
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
	
	/* 输入一个整数，将这个整数以整数的形式逆序输出

	程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001 */
	
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
	
    
    //计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)。不在范围内的不作统计。
    //输入字符在ACSII码范围内(0~127)
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


/*    将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
    所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符*/
    
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
/*   给定n字符串，请对n个字符串按照字典序排列。
    输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。*/
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
		System.out.println("1:"+s.indexOf("tTf"));//存在则返回第一次出现的位置，不存在则返回-1；
		
		System.out.println("2:"+s.lastIndexOf("t"));//存在则返回最后出现的位置，不存在则返回-1；
		System.out.println("3:"+s.charAt(0));//获取指定索引的字符
		System.out.println("4:"+s.trim().charAt(0));//去除空格
		
		System.out.println("5:"+s.replace("s","67"));//将str中的所有s1替换成s2
		
		System.out.println("6:"+s.substring(5,9));//子字符串：第5个字符到第8个
		
		System.out.println("7:"+s.toUpperCase());//转换为大写 ;  toLowerCase()转换为小写
		
		System.out.println("8:"+String.valueOf(true));//转换为字符串
		
		System.out.println("9:"+"true".startsWith("t"));//是否以特定字符串开始 ;  endWith()方法决定是否以特定字符串结束
		System.out.println("10:"+"true".startsWith("u", 2));//"true"的第2位字符是否为u开头
		
		
		StringBuilder sb = new StringBuilder("test");
		sb.append(" ok");
		System.out.println("11:"+sb);
		System.out.println("12:"+sb.reverse());
		System.out.println("13:"+sb);
		System.out.println("14:"+sb.replace(0, 4, "oline"));//sb的0-3个字符替换
		
		StringTokenizer st =new StringTokenizer("Welcome to my school");
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	
	}
	
	
	
}
