package com.nagarro.NagData;

public interface Queue<E> extends NagCollection<E> {

    E peek();

    E dequeue();

    void enqueue(E element);

}
