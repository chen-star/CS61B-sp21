package deque;

import org.checkerframework.checker.units.qual.A;

public class ArrayDeque<T> {

    private static final int GROW_FACTOR = 2;
    private static final double DOWN_SIZING_THRESHOLD = 0.25;

    private T[] items;
    private int size;
    private int first;
    private int last;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        first = -1;
        last = -1;
        size = 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int i = (index + first) % items.length;
        return items[i];
    }


    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * GROW_FACTOR);
        }
        if (isEmpty()) {
            first = 0;
            last = 0;
            items[size++] = item;
            return;
        }

        first--;
        if (first < 0) {
            first = items.length - 1;
        }
        items[first] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * GROW_FACTOR);
        }
        if (isEmpty()) {
            first = 0;
            last = 0;
            items[size++] = item;
            return;
        }

        last++;
        if (last == items.length) {
            last = 0;
        }
        items[last] = item;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T toRemove = items[first];
        items[first] = null;
        first++;
        if (first == items.length) {
            first = 0;
        }
        size--;

        if (size * 1.0 / items.length < DOWN_SIZING_THRESHOLD) {
            resize(items.length / GROW_FACTOR);
        }
        return toRemove;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T toRemove = items[last];
        items[last] = null;
        last--;
        if (last < 0) {
            last = items.length - 1;
        }
        size--;

        if (size * 1.0 / items.length < DOWN_SIZING_THRESHOLD) {
            resize(items.length / GROW_FACTOR);
        }
        return toRemove;
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = get(i);
        }
        first = 0;
        last = size - 1;
        items = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i));
            System.out.print(' ');
        }
        System.out.println();
    }

    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque other = (ArrayDeque) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }
}
