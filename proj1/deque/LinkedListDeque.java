package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private final Node<T> head;
    private final Node<T> tail;
    private int size;

    public LinkedListDeque() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;

        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<T> curr = head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return getRecursive(head.next, 0, index);
    }

    private T getRecursive(Node<T> curr, int i, int index) {
        if (i == index) {
            return curr.data;
        }
        return getRecursive(curr.next, i + 1, index);
    }

    @Override
    public void addFirst(T item) {
        Node<T> toAdd = new Node<>(item);
        toAdd.next = head.next;
        toAdd.next.prev = toAdd;
        toAdd.prev = head;
        head.next = toAdd;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> toAdd = new Node<>(item);
        toAdd.prev = tail.prev;
        toAdd.prev.next = toAdd;
        toAdd.next = tail;
        tail.prev = toAdd;
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> first = head.next;
        head.next = first.next;
        first.next.prev = head;
        size--;
        return first.data;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> last = tail.prev;
        tail.prev = last.prev;
        last.prev.next = tail;
        size--;
        return last.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node<T> curr = head.next;
        while (curr != tail) {
            System.out.print(curr.data);
            System.out.println(' ');
            curr = curr.next;
        }
        System.out.println();
    }

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        private Node(T data) {
            this.data = data;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (this == other) {
            return true;
        }
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node<T> curr;

        private LinkedListDequeIterator() {
            curr = head.next;
        }


        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public T next() {
            T item = curr.data;
            curr = curr.next;
            return item;
        }
    }
}
