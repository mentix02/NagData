package com.nagarro.NagData;

/**
 * A <tt>Queue</tt> implemented using a <tt>LinkedList</tt>.
 *
 * @param <E> type of element to contain
 * @author mentix02
 * @see com.nagarro.NagData.LinkedList
 */
public class Queue<E> extends LinkedList<E> {

    /**
     * Adds element to end (technically, beginning)
     *
     * @param element item to insert
     */
    public void enqueue(E element) {
        this.addLast(element);
    }

    /**
     * Returns and removes value at top of queue
     *
     * @return value at top or null if queue is empty
     */
    public E dequeue() {
        return this.removeLast();
    }

    public E peek() {
        return this.getLast();
    }

}
