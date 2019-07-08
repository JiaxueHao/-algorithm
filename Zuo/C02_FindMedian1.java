package Zuo;
/*使用PrioityQueue实现堆
 * 1.维持两个heap,一个是最小堆，一个是最大堆。

2.一直使maxHeap的size>=minHeap.

3. 当两边size相同时，比较新插入的value,如果它大于minHeap的最大值，把它插入到minHeap。并且把minHeap的最小值移动到maxHeap。*/
import java.util.*;
 
 public class C02_FindMedian1 {
     private static PriorityQueue<Integer> maxHeap, minHeap;
 
     public static void main(String[] args) {
 
         Comparator<Integer> revCmp = new Comparator<Integer>() {
             @Override
             //降序
             //return 正数  right在前
             //return 负数  left在前
             //谁大谁在前
             public int compare(Integer left, Integer right) {
//                 return right.compareTo(left);
               return right-left;
             }
         };
 
         // Or you can use Collections' reverseOrder method as follows.
         // Comparator<Integer> revCmp = Collections.reverseOrder();
 
         maxHeap = new PriorityQueue<Integer>(revCmp);
         minHeap = new PriorityQueue<Integer>();
 
         addNumber(6);
         addNumber(4);
         addNumber(3);
         addNumber(10);
         addNumber(12);
         System.out.println(minHeap);
         System.out.println(maxHeap);
         System.out.println(getMedian());
 
         addNumber(5);
         System.out.println(minHeap);
         System.out.println(maxHeap);
         System.out.println(getMedian());
 
         addNumber(7);
         addNumber(8);
         System.out.println(minHeap);
         System.out.println(maxHeap);
         System.out.println(getMedian());
     }
 
     /*
      * Note: it maintains a condition that maxHeap.size() >= minHeap.size()
      */
     public static void addNumber(int value) {
    	 //maxHeap的size与minHeap的size相同：
         if (maxHeap.size() == minHeap.size()) {
        	//如果value>maxHeap的最大值，把它插入到minHeap。并且把minHeap的最小值移动到maxHeap
             if (maxHeap.peek() != null && value > maxHeap.peek()) {
                 minHeap.offer(value);
                 maxHeap.offer(minHeap.poll());
             } 
            //如果value<=minHeap的最大值，把它插入到maxHeap
             else {
                 maxHeap.offer(value);
             }
         } 
       //maxHeap的size>minHeap的size：
         else {
        	 //如果value < maxHeap的最大值，把maxHeap的最大值移动到minHeap,并且把value插入到maxHeap。
             if (value < maxHeap.peek()) {
            	 maxHeap.offer(value);
                 minHeap.offer(maxHeap.poll());
             } 
             //如果value>==maxHeap的最大值，把它插入到minHeap
             else {
                 minHeap.offer(value);
             }
         }
     }

     /*
      * If maxHeap and minHeap are of different sizes, 
     * then maxHeap must have one extra element.
      */
     public static double getMedian() {
         if (maxHeap.isEmpty()) {
             return -1; 
         }
         
         if (maxHeap.size() == minHeap.size()) {
             return (double)(minHeap.peek() + maxHeap.peek())/2;
         } else {
             return maxHeap.peek();
         }
     }
 }
