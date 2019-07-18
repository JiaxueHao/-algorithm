package interest;

import java.util.*;

//ʹ�ù�ϣ��������ʵ��LRU�������ÿ���ڵ�Ϊ��ϣ�ڵ㣬����̶�����
//��Ʒ�����¼���������������ǰ�棬���ӣ�ɾ������ѯ��Ʒ�����޳��ȱ�����Ʒ��Ϣ
public class LRU_GoodsBrowse2<K, V> {
	
    private int MAX_CACHE_SIZE;
    private Entry<K, V> head;//ͷָ�룬�����ڵ㣬û���õ�pre��next
    private Entry<K, V> tail;//βָ�룬�����ڵ㣬û���õ�pre��next
    private HashMap<Long, Entry<K, V>> cache;//��ϣ��+����
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
    //��ʼ��ϣ��Ϊ�գ�����Ϊ0
    public LRU_GoodsBrowse2(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        cache = new HashMap<>();
    }
    public boolean isEmpty() {
    	return cache.size()==0;
    }
    public void addGood(String goodName, String type, String color, Integer size) {
    	
    	Long key = findGood(goodName);
    	
    	//��Ʒ������
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
    	//��Ʒ����
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


    private Long generate() {
    	//���ؿ��õ���СID
    	//������泬�����ֵ
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
        	//����Ʒ����
        	if(infos.values.GoodName.equals(goodName)) {
        		return key;
        	}
    	}
    	return null;
    }

   
//����β�ڵ�
    private void removeTail() {
        if (tail != null) {
            Entry<K, V> prev = tail.pre;
            Long key = findGood(tail.values.GoodName);
            cache.remove(key);
            ids.remove(key);
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
        lru2.addGood("�����", "����Ʒ", "��ɫ", 1);
        System.out.println(lru2);
        lru2.addGood("��ˢ", "����Ʒ", "��ɫ", 2);
        System.out.println(lru2);
        lru2.addGood("ϴ����", "����Ʒ", "��ɫ", 3);
        System.out.println(lru2);
        lru2.addGood("ľ��", "����Ʒ", "��ɫ", 4);
        System.out.println(lru2);
        lru2.addGood("����", "����", "��ɫ", 5);
        System.out.println(lru2);
        lru2.addGood("����", "����Ʒ", "��ɫ", 6);
        System.out.println(lru2);
        lru2.addGood("��ë��", "����", "��ɫ", 5);
        System.out.println(lru2);
        lru2.addGood("������", "����Ʒ", "��ɫ", 6);
        System.out.println(lru2);

        lru2.getGood("ľ��");
        System.out.println(lru2);
        
        lru2.remove("ľ��");
        System.out.println(lru2);
        lru2.remove("��ë��");
        System.out.println(lru2);
        
        lru2.addGood("ľ��", "����Ʒ", "��ɫ", 4);
        System.out.println(lru2);
        System.out.println(lru2.isEmpty());
        
    }
}