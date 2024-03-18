package cache;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * An LRU cache mechanism, based on LinkedHashMap.
 * This cache has a fixed maximum number of elements. If the cache is full and another entry is added,
 * the LRU (least recently used) entry is dropped.
 * If cache key is/are expired then all the expired caches are evicted.
 * This class is thread-safe. All methods of this class are synchronized.
 */

public class LRUCache<K, V> implements Cache<K,V> {

    private static final float hashTableLoadFactor = 0.75f;

    private LinkedHashMap<K, V> map;
    private LinkedHashMap<K, Long> timestampKeyMap;
    private int cacheSize;
    private long evictionTime;

    /**
     * Creates a new LRU cache.
     *
     * @param cacheSize    the maximum number of entries that will be kept in this cache.
     * @param evictionTime remove key from LRU cache which has been idle for greater than or equal to this time.
     */
    LRUCache(int cacheSize, long evictionTime) {
        this.cacheSize = cacheSize;
        this.evictionTime = evictionTime;

        int hashTableCapacity = (int) Math.ceil(cacheSize / hashTableLoadFactor) + 1;
        timestampKeyMap = new LinkedHashMap<>(hashTableCapacity, hashTableLoadFactor);
        map = new LinkedHashMap<K, V>(hashTableCapacity, hashTableLoadFactor, true) {
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LRUCache.this.cacheSize;
            }
        };
    }

    /**
     * Retrieves an entry from the cache and evict the expired keys.
     * The retrieved entry becomes the MRU (most recently used) entry.
     *
     * @param key the key whose associated value is to be returned.
     * @return the value associated to this key, or null if no value with this key exists in the cache.
     */
    @Override
    public synchronized V get(K key) {
        evictionByAccessTime();
        timestampKeyMap.put(key, getCurrentDateTimeInMillis());
        return map.get(key);
    }

    /**
     * Adds an entry to this cache.
     * The new entry becomes the MRU (most recently used) entry.
     * If an entry with the specified key already exists in the cache, it is replaced by the new entry.
     * If the cache is full, the LRU (least recently used) entry is removed from the cache.
     *
     * @param key   the key with which the specified value is to be associated.
     * @param value a value to be associated with the specified key.
     */
    @Override
    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    private void evictionByAccessTime() {
        long currentTime = getCurrentDateTimeInMillis();
        List<K> staleCacheKeys = new ArrayList<>();
        for (Map.Entry<K, Long> entry : timestampKeyMap.entrySet()) {
            if ((currentTime - entry.getValue()) >= evictionTime) {
                staleCacheKeys.add(entry.getKey());
            }
        }
        if (!staleCacheKeys.isEmpty()) {
            staleCacheKeys.forEach(cacheKey -> {
                timestampKeyMap.remove(cacheKey);
                map.remove(cacheKey);
            });
        }
    }

    private long getCurrentDateTimeInMillis(){
     return ZonedDateTime.now(ZoneId.of("UTC")).toLocalDateTime().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

}
