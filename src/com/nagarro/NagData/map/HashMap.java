package com.nagarro.NagData.map;

import com.nagarro.NagData.NagCollection;

import java.util.Iterator;
import java.util.Objects;

/**
 * This iterator is the definition of black magic. Settle down cuz it's
 * going to be a bumpy ride.
 *
 * <p>
 * First, we initialize some private variables.
 *     <ul>
 *         <li>{@link #bucketIdx} is used to keep track of the last bucket index accessed</li>
 *         <li>{@link #iteratedEntryCount} is used to keep track of how many entries have
 *         been returned.</li>
 *         <li>{@link #hashMap} is the primary reference to the hash map to iterate over</li>
 *         <li>{@link #currentEntry} is the current node of MapEntry the iterator may be on</li>
 *     </ul>
 *
 * <p>
 * Now for the actual implementation.
 * <p>
 * The {@link #hasNext()} function only checks whether the iterator holds a valid {@link #currentEntry} OR
 * if it hasn't yet returned the {@link HashMap#size() number of entries} stored in the {@link #hashMap}.
 * <p>
 * It requires both of these conditions to be false in order to stop iteration.
 *
 * <p>
 * In the {@link #next()} method, we start by checking for a valid currentEntry. If we have one, we extract
 * it into a temporary variable that is to be returned and replace the entry with its {@link MapEntry#getNext() next}
 * member. If the iterator DOESN'T hold a non-null entry, we simply try to get the next bucket aka the head
 * of a possibly non-null {@link MapEntry}. Recursively calling next results in the same check for nullability.
 * <p>
 * We don't need to perform any additional checks on accessing the bucket using
 * <code>hashMap.buckets[bucketIdx++]</code> since we can be sure next() won't be called if the requirements for
 * hasNext() haven't been met - implying there are still entries to be returned.
 *
 * @param <K> type of key
 * @param <V> type of value
 * @author mentix02
 */
class HashMapIterator<K, V> implements Iterator<MapEntry<K, V>> {

    private int bucketIdx = 0;
    private int iteratedEntryCount = 0;
    private MapEntry<K, V> currentEntry;
    private final HashMap<K, V> hashMap;

    public HashMapIterator(HashMap<K, V> hashMap) {
        this.hashMap = hashMap;
    }

    public boolean hasNext() {
        return !Objects.isNull(currentEntry) ||
                iteratedEntryCount < hashMap.size();
    }

    public MapEntry<K, V> next() {

        // First check if we have a currently populated entry
        if (!Objects.isNull(currentEntry)) {
            // Keep going on until we reach the end
            MapEntry<K, V> toReturn = currentEntry;
            currentEntry = currentEntry.getNext();
            iteratedEntryCount++;
            return toReturn;
        }

        currentEntry = hashMap.buckets[bucketIdx++];
        return next();

    }

}

/**
 * A simple hash table implemented using an array of linked lists.
 *
 * @param <K> type of key
 * @param <V> type of value
 * @author mentix02
 */
public class HashMap<K, V> implements NagCollection<MapEntry<K, V>> {

    private int capacity;
    private int length = 0;
    protected MapEntry<K, V>[] buckets;

    // Percent of hashmap that can be full before we need to rehash.
    private static final float LFACTOR = .75f;

    // Not exactly sure why so many ref implementations use
    // these left bit shifts, but I'll follow suit, I guess.
    public static final int MIN_INITIAL_CAPACITY = 1 << 4;

    public HashMap() {
        this(MIN_INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        this.capacity = capacity;
        buckets = new MapEntry[capacity];
    }

    // Read from hash map

    public int size() {
        return length;
    }

    public int bucketsLength() {
        return buckets.length;
    }

    public V get(K key) {
        int bucketIdx = getHash(key) % buckets.length;
        MapEntry<K, V> bucket = buckets[bucketIdx];

        while (bucket != null) {
            if (bucket.getKey().equals(key)) {
                return bucket.getValue();
            }
            bucket = bucket.getNext();
        }
        return null;
    }

    // Manipulate

    public void put(K key, V value) {

        ensureCapacity();

        MapEntry<K, V> entry = new MapEntry<>(key, value);

        // Get bucket index and pray it won't be a collision
        int bucketIdx = getHash(key) % buckets.length;

        MapEntry<K, V> existing = buckets[bucketIdx];

        if (Objects.isNull(existing)) {
            // Yay!
            buckets[bucketIdx] = entry;
            length++;
        } else {
            // RIP folks, we've had a collision
            while (existing.getNext() != null) {
                if (existing.getKey().equals(key)) {
                    existing.setValue(value);
                    return;
                }
                existing = existing.getNext();
            }
            if (existing.getKey().equals(key)) {
                existing.setValue(value);
            } else {
                existing.setNext(entry);
                length++;
            }
        }
    }

    @Override
    public Iterator<MapEntry<K, V>> iterator() {
        return new HashMapIterator<>(this);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (MapEntry<K, V> mapEntry : buckets) {
            if (mapEntry != null) {
                // Traverse linked list... because who
                // does not like that.
                while (mapEntry != null) {
                    stringBuilder.append(mapEntry);
                    if (mapEntry.hasNext()) {
                        stringBuilder.append(",");
                    }
                    mapEntry = mapEntry.getNext();
                }
                stringBuilder.append(",");
            }
        }
        return String.format("%c%s%c", '{', stringBuilder, '}');
    }

    // Private methods

    private int getHash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode());
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (length == (int) (LFACTOR * capacity)) {
            length = 0;
            capacity *= 2;
            MapEntry<K, V>[] prevBuckets = buckets;
            buckets = new MapEntry[capacity];

            for (MapEntry<K, V> oldBucket : prevBuckets) {
                while (oldBucket != null) {
                    put(oldBucket.getKey(), oldBucket.getValue());
                    oldBucket = oldBucket.getNext();
                }
            }
        }
    }

}
