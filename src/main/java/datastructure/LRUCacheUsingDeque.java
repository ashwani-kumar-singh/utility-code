package datastructure;

import java.util.*;
import java.util.LinkedList;

public class LRUCacheUsingDeque<K> {
    private final Deque<K> doublyQueue;
    // store references of key in cache
    private final Set<K> hashSet;
    // maximum capacity of cache
    private final int CACHE_SIZE;

    LRUCacheUsingDeque(int capacity) {
        this.doublyQueue = new LinkedList<>();
        this.hashSet = new LinkedHashSet<>();
        this.CACHE_SIZE = capacity;
    }

    synchronized void get(K key) {
        if (!hashSet.contains(key)) {
            if (doublyQueue.size() == CACHE_SIZE) {
                K last = doublyQueue.removeLast();
                hashSet.remove(last);
            }
        } else {
            doublyQueue.remove(key);
        }
        doublyQueue.push(key);
        hashSet.add(key);
    }

    public void display() {
        for (K k : doublyQueue) {
            System.out.print(k + " ");
        }
    }

    public static void main(String[] args) {
        LRUCacheUsingDeque<Integer> cache = new LRUCacheUsingDeque<>(4);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(1);
        cache.get(4);
        cache.get(5);
        cache.get(2);
        cache.get(2);
        cache.get(1);
        cache.display();
    }

}
