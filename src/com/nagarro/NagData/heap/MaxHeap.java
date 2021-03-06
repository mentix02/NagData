package com.nagarro.NagData.heap;

import com.nagarro.NagData.Vector;

/**
 * Used to extract objects of the highest priority. Inherits
 * from {@link AbstractHeap}.
 *
 * @param <E> type of data to store
 * @author mentix02
 */
public class MaxHeap<E extends Comparable<E>> extends AbstractHeap<E> {

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