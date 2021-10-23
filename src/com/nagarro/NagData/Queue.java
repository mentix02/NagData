package com.nagarro.NagData;

public interface Queue<E> {

    E peek();

    E dequeue();

    void enqueue(E element);

}
