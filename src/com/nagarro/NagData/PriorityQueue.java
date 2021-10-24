package com.nagarro.NagData;

import java.util.Iterator;
import java.util.Objects;

/**
 * A terrible iterator - the easiest way to implement
 * @param <E>
 */
class QueueIterator<E extends Comparable<E>> implements Iterator<E> {

    private final Queue<E> queue;

    public QueueIterator(Queue<E> queue) {
        this.queue = new PriorityQueue<>(queue);
    }

    @Override
    public boolean hasNext() {
        return queue.size() != 0;
    }

    public E next() {
        return queue.dequeue();
    }

}

/**
 * A simple max Priority Queue ADT implemented via {@link MaxHeap}.
 * This class is nothing but a wrapper around {@link MaxHeap} to
 * implement the API provided by {@link Queue}.
 *
 * @param <E> type of data to store
 */
public class PriorityQueue<E extends Comparable<E>> extends MaxHeap<E> implements Queue<E> {

    public PriorityQueue(PriorityQueue<E> priorityQueue) {
        if (Objects.isNull(priorityQueue))
            throw new IllegalArgumentException("Cannot copy " + this.getClass().getName() + " to null.");
        priorityQueue.heap = this.heap;
    }

    @Override
    public void enqueue(E element) {
        this.add(element);
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            return null;
        return poll();
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return heap.get(0);
    }

    public boolean contains(E toFind) {
        for (E el : heap)
            if (el.equals(toFind))
                return true;
        return false;
    }

    public Iterator<E> iterator() {
        return new QueueIterator<>(this);
    }

}
