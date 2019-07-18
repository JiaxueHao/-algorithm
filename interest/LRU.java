package interest;



import java.util.HashMap;

//使用哈希表与链表实现LRU，链表的每个节点为哈希节点
public class LRU<K, V> {
	
    private final int MAX_CACHE_SIZE;
    private Entry<K, V> head;//头指针，辅助节点，没有用到pre和next
    private Entry<K, V> tail;//尾指针，辅助节点，没有用到pre和next
    private HashMap<K, Entry<K, V>> cache;//哈希表+链表


    private static class Entry<K, V> {
        Entry<K, V> pre;
        Entry<K, V> next;
        K key;
        V value;
    }
    
    public LRU(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        cache = new HashMap<>();
    }

    public void put(K key, V value) {
        Entry<K, V> entry = cache.get(key);
        //LRU缓存中没有这个Key
        if (entry == null) {
        	//如果缓存超过最大值
            if (cache.size() >= MAX_CACHE_SIZE) {
                cache.remove(tail.key);
                removeTail();
            }
            entry = new Entry<>();
            entry.key = key;
            entry.value = value;
            moveToHead(entry);
            cache.put(key, entry);
            //key存在，将key提前
        } else {
            entry.value = value;
            moveToHead(entry);
        }
    }

    public V get(K key) {
        Entry<K, V> entry = cache.get(key);
        if (entry == null) {
            return null;
        }
        moveToHead(entry);
        return entry.value;
    }

    public void remove(K key) {
        Entry<K, V> entry = cache.get(key);
        if (entry != null) {
            if (entry == head) {
                Entry<K, V> next = head.next;
                head.next = null;
                head = next;
                head.pre = null;
            } else if (entry == tail) {
                Entry<K, V> prev = tail.pre;
                tail.pre = null;
                tail = prev;
                tail.next = null;
            } else {
                entry.pre.next = entry.next;
                entry.next.pre = entry.pre;
            }
            cache.remove(key);
        }
    }
//更新尾节点
    private void removeTail() {
        if (tail != null) {
            Entry<K, V> prev = tail.pre;
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



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Entry<K, V> entry = head;
        while (entry != null) {
            stringBuilder.append(String.format("%s:%s ", entry.key, entry.value));
            entry = entry.next;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LRU<Integer, Integer> lru2 = new LRU<>(5);
        lru2.put(1, 1);
        System.out.println(lru2);
        lru2.put(2, 2);
        System.out.println(lru2);
        lru2.put(3, 3);
        System.out.println(lru2);
        lru2.get(1);
        System.out.println(lru2);
        lru2.put(4, 4);
        lru2.put(5, 5);
        lru2.put(6, 6);
        lru2.put(6, 7);
        lru2.remove(6);
        lru2.remove(3);
        lru2.remove(7);
        lru2.get(4);
        System.out.println(lru2);
    }
}