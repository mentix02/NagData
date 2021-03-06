package com.nagarro.NagData.heap;

import com.nagarro.NagData.Vector;

/**
 * Used to extract objects of the lowest priority. Inherits
 * from {@link AbstractHeap}.
 *
 * @param <E> type of data to store
 * @author mentix02
 */
public class MinHeap<E extends Comparable<E>> extends AbstractHeap<E> {

    public MinHeap() {
    }

    public MinHeap(Vector<E> vector) {
        super(vector);
    }

    /**
     * @param left  object tested to be lesser
     * @param right object tested to be greater
     * @return true if left < right else false
     */
    protected boolean comparator(E left, E right) {
        return left.compareTo(right) < 0;
    }

}
