package interest;

import java.util.*;

//使用哈希表与链表实现LRU，链表的每个节点为哈希节点，链表固定长度
//商品浏览记录：最新浏览的排在前面，增加，删除，查询商品，无限长度保存商品信息
public class LRU_GoodsBrowse2<K, V> {
	
    private int MAX_CACHE_SIZE;
    private Entry<K, V> head;//头指针，辅助节点，没有用到pre和next
    private Entry<K, V> tail;//尾指针，辅助节点，没有用到pre和next
    private HashMap<Long, Entry<K, V>> cache;//哈希表+链表
    private TreeSet<Long> ids = new TreeSet<>();

    private static class Entry<K, V> {
        Entry<K, V> pre;
        Entry<K, V> next;
        Info<String, String> values = new Info<String, String>(); 
        
        private static class Info<K,V> {
        	String GoodName;
        	String Type;
            String Color;
            Integer Size;
        }
    }
    //初始哈希表为空，长度为0
    public LRU_GoodsBrowse2(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        cache = new HashMap<>();
    }
    public boolean isEmpty() {
    	return cache.size()==0;
    }
    public void addGood(String goodName, String type, String color, Integer size) {
    	
    	Long key = findGood(goodName);
    	
    	//商品不存在
    	if(key == null) {
            Long id = generate();
            	
                Entry<K, V> entry = new Entry<>();
                entry.values.GoodName = goodName;
                entry.values.Type = type;
                entry.values.Color = color;
                entry.values.Size = size;
                ids.add(id);
                moveToHead(entry);
                cache.put(id, entry);
    	}else{
    		Entry<K,V> infos = cache.get(key);
    		moveToHead(infos);
    	}
   } 
    
    public void remove(String goodName) {
    	Long key = findGood(goodName);
    	//商品存在
    	if(key != null) {
    		
    		Entry<K,V> infos = cache.get(key);
    		moveToHead(infos);
            if (infos == head) {
                Entry<K, V> next = head.next;
                head.next = null;
                head = next;
                head.pre = null;
            } else if (infos == tail) {
                Entry<K, V> prev = tail.pre;
                tail.pre = null;
                tail = prev;
                tail.next = null;
            } else {
            	infos.pre.next = infos.next;
            	infos.next.pre = infos.pre;
            }
            cache.remove(key);
            ids.remove(key);
        }
    }
    
    public Long getGood(String goodName){
    	Long key = findGood(goodName);
    	if(key != null) {
    		Entry<K,V> infos = cache.get(key);
    		moveToHead(infos);
    		return key;
    	}else {
    		return null;
    	}
    	
    }
    
    private void moveToHead(Entry<K, V> entry) {
        
        //列表为空时
        if (head == null || tail == null) {
            head = tail = entry;
            return;
        }
    	
    	//如果key节点为头结点，不作处理
    	if (entry == head) {
            return;
        }
    	
    	 //key节点为尾节点时，更新尾节点，尾节点指针指向key的前驱节点
        if (entry == tail) {
            Entry<K, V> prev = entry.pre;
            if (prev != null) {
                tail.pre = null;
                tail = prev;
                tail.next = null;
            }
        }
    	
    	//其他位置：相当于删除当前位置的key节点
    	//key的前节点的后继节点改为key的后继节点
        if (entry.pre != null) {
            entry.pre.next = entry.next;
        }
        //key的后继节点的前驱阶段改为key的前节点
        if (entry.next != null) {
            entry.next.pre = entry.pre;
        }
        
        //设置头结点位置的key节点，更新头结点
        entry.next = head;
        head.pre = entry;
        entry.pre = null;
        head = entry;
    }


    private Long generate() {
    	//返回可用的最小ID
    	//如果缓存超过最大值
        if (cache.size() >= MAX_CACHE_SIZE) {
        	MAX_CACHE_SIZE = MAX_CACHE_SIZE+1;
        }
        
		TreeSet<Long> old = new TreeSet<>();
		for(Long i = 0l;i<MAX_CACHE_SIZE;i++) {
			old.add(i);
		}
		old.removeAll(ids);
		return old.first();
	}
    
    private Long findGood(String goodName){
    	for(Map.Entry<Long, Entry<K, V>> good:cache.entrySet()) {
        	Entry<K, V> infos = good.getValue();
        	Long key = good.getKey();
        	//该商品存在
        	if(infos.values.GoodName.equals(goodName)) {
        		return key;
        	}
    	}
    	return null;
    }

   
//更新尾节点
    private void removeTail() {
        if (tail != null) {
            Entry<K, V> prev = tail.pre;
            Long key = findGood(tail.values.GoodName);
            cache.remove(key);
            ids.remove(key);
            //空链表
            if (prev == null) {
                head = null;
                tail = null;
            } else {
            	//更新尾节点
                tail.pre = null;
                tail = prev;
                tail.next = null;
            }
            
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Entry<K, V> entry = head;
        while (entry != null) {
        	Long key = findGood(entry.values.GoodName);
            stringBuilder.append(String.format("%s:%s-%s-%s-%s ",key,entry.values.GoodName, entry.values.Color,entry.values.Type,entry.values.Size));
            entry = entry.next;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LRU_GoodsBrowse2<Integer, Integer> lru2 = new LRU_GoodsBrowse2<>(5);
        System.out.println(lru2.isEmpty());
        lru2.addGood("吹风机", "日用品", "黑色", 1);
        System.out.println(lru2);
        lru2.addGood("牙刷", "日用品", "白色", 2);
        System.out.println(lru2);
        lru2.addGood("洗面奶", "日用品", "黄色", 3);
        System.out.println(lru2);
        lru2.addGood("木梳", "日用品", "棕色", 4);
        System.out.println(lru2);
        lru2.addGood("汽车", "娱乐", "红色", 5);
        System.out.println(lru2);
        lru2.addGood("牙膏", "日用品", "红色", 6);
        System.out.println(lru2);
        lru2.addGood("羽毛球", "娱乐", "红色", 5);
        System.out.println(lru2);
        lru2.addGood("洁面仪", "日用品", "红色", 6);
        System.out.println(lru2);

        lru2.getGood("木梳");
        System.out.println(lru2);
        
        lru2.remove("木梳");
        System.out.println(lru2);
        lru2.remove("羽毛球");
        System.out.println(lru2);
        
        lru2.addGood("木梳", "日用品", "棕色", 4);
        System.out.println(lru2);
        System.out.println(lru2.isEmpty());
        
    }
}