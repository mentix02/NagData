package com.nagarro.NagData.queue;

import com.nagarro.NagData.LinkedList;

/**
 * A <tt>LinearQueue</tt> implemented using a <tt>LinkedList</tt>.
 *
 * @param <E> type of element to contain
 * @author mentix02
 * @see com.nagarro.NagData.LinkedList
 */
public class LinearQueue<E> extends LinkedList<E> implements Queue<E> {

    public LinearQueue() {
    }

    public LinearQueue(LinearQueue<E> queue) {
        for (E el : queue)
            enqueue(el);
    }

    /**
     * Reads element inserted the first time in queue.
     *
     * @return value at top or null if queue is empty
     */
    public E peek() {
        return this.getFirst();
    }

    /**
     * Returns and removes value at top of queue
     *
     * @return value at top or null if queue is empty
     */
    public E dequeue() {
        return this.removeFirst();
    }

    /**
     * Adds element to beginning of queue
     *
     * @param element item to insert
     */
    public void enqueue(E element) {
        this.addLast(element);
    }

    @Override
    public Queue<E> makeReverseQueue() {
        LinearQueue<E> lq = new LinearQueue<>(this);
        lq.selfReverse();
        return lq;
    }

}
