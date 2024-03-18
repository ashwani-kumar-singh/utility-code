package cache;

public class LRUCacheUtility {
    private final LRUCache<String, Object> lruCache;
    private int cacheSize = 8;
    private long evictionTime = 24 * 60 * 60 * 1000;

    public LRUCacheUtility() {
        this.lruCache = new LRUCache<>(cacheSize, evictionTime);
    }

    public LRUCacheUtility(int cacheSize, long evictionTime) {
        this.cacheSize = cacheSize;
        this.evictionTime = evictionTime;
        this.lruCache = new LRUCache<>(cacheSize, evictionTime);
    }

    public Object get(String key) {
        return lruCache.get(key);
    }

    public void put(String key, Object value) {
        lruCache.put(key, value);
    }
}
