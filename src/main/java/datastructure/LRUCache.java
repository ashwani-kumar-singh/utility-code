package datastructure;

import java.util.*;
import java.lang.*;
import java.util.HashSet;

public class LRUCache<K, V> {
    private static final float hashTableLoadFactor = 0.75f;
    private final int cacheSize;
    private final int evictionTime;
    private final Map<K, V> map;
    private final Map<K, Long> timestampKeyMap;


    public LRUCache(int cacheSize, int evictionTime) {
        this.cacheSize = cacheSize;
        this.evictionTime = evictionTime;

        int hashTableCapacity = (int) Math.ceil(cacheSize / hashTableLoadFactor) + 1;
        this.timestampKeyMap = new LinkedHashMap<>(hashTableCapacity, hashTableLoadFactor);
        this.map = new LinkedHashMap<K, V>(hashTableCapacity, hashTableLoadFactor, true) {
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() >= LRUCache.this.cacheSize;
            }
        };
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized void get(K key) {
        evictionByAccessTime();
        timestampKeyMap.put(key, System.currentTimeMillis());
        map.get(key);
    }

    private void evictionByAccessTime() {
        Set<K> staleKeys = new HashSet<>();
        timestampKeyMap.forEach((key, timeStamp) -> {
            if (System.currentTimeMillis() - timeStamp >= evictionTime) {
                staleKeys.add(key);
            }
        });

        if (!staleKeys.isEmpty()) {
            staleKeys.forEach(staleKey -> {
                map.remove(staleKey);
                timestampKeyMap.remove(staleKey);
            });
        }
    }

    public void display() {
        map.forEach((key, value) -> System.out.println(key+ " - " + value));
    }

    public static void main(String[] args) {
        LRUCache<Integer, Object> cache = new LRUCache<>(4, 1000);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.put(1, "Z");

        cache.get(1);
        cache.get(2);
        cache.get(3);

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(2, "Y");

        cache.get(1);
        cache.get(4);
        cache.get(5);

        cache.put(2, "M");
        cache.put(1, "N");
        cache.get(2);
        cache.get(2);
        cache.get(1);

        cache.display();
    }
}
