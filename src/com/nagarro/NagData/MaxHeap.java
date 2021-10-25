package com.nagarro.NagData;

/**
 * Used to extract objects of the highest priority. Inherits
 * from {@link Heap}.
 *
 * @param <E> type of data to store
 * @author mentix02
 */
public class MaxHeap<E extends Comparable<E>> extends Heap<E> {

    public MaxHeap() {

    }

    public MaxHeap(Vector<E> vector) {
        super(vector);
    }

    /**
     * @param left  object tested to be greater
     * @param right object tested to be lesser
     * @return true if left > right else false
     */
    protected boolean comparator(E left, E right) {
        return left.compareTo(right) > 0;
    }

}