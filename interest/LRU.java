package interest;



import java.util.HashMap;

//ʹ�ù�ϣ��������ʵ��LRU�������ÿ���ڵ�Ϊ��ϣ�ڵ�
public class LRU<K, V> {
	
    private final int MAX_CACHE_SIZE;
    private Entry<K, V> head;//ͷָ�룬�����ڵ㣬û���õ�pre��next
    private Entry<K, V> tail;//βָ�룬�����ڵ㣬û���õ�pre��next
    private HashMap<K, Entry<K, V>> cache;//��ϣ��+����


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
        //LRU������û�����Key
        if (entry == null) {
        	//������泬�����ֵ
            if (cache.size() >= MAX_CACHE_SIZE) {
                cache.remove(tail.key);
                removeTail();
            }
            entry = new Entry<>();
            entry.key = key;
            entry.value = value;
            moveToHead(entry);
            cache.put(key, entry);
            //key���ڣ���key��ǰ
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
//����β�ڵ�
    private void removeTail() {
        if (tail != null) {
            Entry<K, V> prev = tail.pre;
            //������
            if (prev == null) {
                head = null;
                tail = null;
            } else {
            	//����β�ڵ�
                tail.pre = null;
                tail = prev;
                tail.next = null;
            }
        }
    }

    private void moveToHead(Entry<K, V> entry) {
        
        //�б�Ϊ��ʱ
        if (head == null || tail == null) {
            head = tail = entry;
            return;
        }
    	
    	//���key�ڵ�Ϊͷ��㣬��������
    	if (entry == head) {
            return;
        }
    	
    	 //key�ڵ�Ϊβ�ڵ�ʱ������β�ڵ㣬β�ڵ�ָ��ָ��key��ǰ���ڵ�
        if (entry == tail) {
            Entry<K, V> prev = entry.pre;
            if (prev != null) {
                tail.pre = null;
                tail = prev;
                tail.next = null;
            }
        }
    	
    	//����λ�ã��൱��ɾ����ǰλ�õ�key�ڵ�
    	//key��ǰ�ڵ�ĺ�̽ڵ��Ϊkey�ĺ�̽ڵ�
        if (entry.pre != null) {
            entry.pre.next = entry.next;
        }
        //key�ĺ�̽ڵ��ǰ���׶θ�Ϊkey��ǰ�ڵ�
        if (entry.next != null) {
            entry.next.pre = entry.pre;
        }

        
        //����ͷ���λ�õ�key�ڵ㣬����ͷ���
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