package com.nagarro.NagData.map;

import java.util.Objects;

public class MapEntry<K, V> {

    private V value;
    final private K key;
    private MapEntry<K, V> next;
    private MapEntry<K, V> prev;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Getters

    public K getKey() {
        return key;
    }

    protected boolean hasNext() {
        return !Objects.isNull(next);
    }

    protected boolean hasPrev() {
        return !Objects.isNull(prev);
    }

    public V getValue() {
        return value;
    }

    public MapEntry<K, V> getNext() {
        return next;
    }

    public MapEntry<K, V> getPrev() {
        return prev;
    }

    // Setters

    public void setValue(V value) {
        this.value = value;
    }

    public void setNext(MapEntry<K, V> next) {
        this.next = next;
    }

    public void setPrev(MapEntry<K, V> prev) {
        this.prev = prev;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj instanceof MapEntry) {
            MapEntry<K, V> entry = (MapEntry<K, V>) obj;

            return key.equals(entry.getKey()) && value.equals(entry.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 17 * hash + ((key == null) ? 0 : key.hashCode());
        hash = 17 * hash + ((value == null) ? 0 : value.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return String.format("%s=%s", key.toString(), value.toString());
    }

}
