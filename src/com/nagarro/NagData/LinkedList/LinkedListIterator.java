package com.nagarro.NagData.LinkedList;

import java.util.Iterator;

class LinkedListIterator<E> implements Iterator<E> {

    LinkedListNode<E> current;

    public LinkedListIterator(LinkedList<E> list) {
        current = list.getHead();
    }

    public boolean hasNext() {
        return current != null;
    }

    public E next() {
        E data = current.getData();
        current = current.getNextNode();
        return data;
    }


}
