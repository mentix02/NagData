package com.nagarro.NagData;

import java.util.Iterator;

/**
 * A terrible iterator - the easiest way to implement one for
 * priority queues.
 *
 * @param <E>
 */
class QueueIterator<E extends Comparable<E>> implements Iterator<E> {

    private final Queue<E> copyQueue;

    @SuppressWarnings("unchecked")
    public QueueIterator(Queue<E> queue) {
        copyQueue = (Queue<E>) queue.clone();
    }

    @Override
    public boolean hasNext() {
        return copyQueue.size() != 0;
    }

    public E next() {
        return copyQueue.dequeue();
    }

}

public interface Queue<E> extends NagCollection<E> {

    E peek();

    E dequeue();

    Object clone();

    boolean isEmpty();

    Queue<E> reverse();

    void enqueue(E element);

}
