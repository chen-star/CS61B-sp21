package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> {
    private final Comparator<T> defaultComparator;
    private final ArrayDeque<T> items;

    public MaxArrayDeque(Comparator<T> c) {
        items = new ArrayDeque<>();
        defaultComparator = c;
    }

    public void addFirst(T item) {
        items.addFirst(item);
    }

    public void addLast(T item) {
        items.addLast(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

    public void printDeque() {
        items.printDeque();
    }

    public T removeFirst() {
        return items.removeFirst();
    }

    public T removeLast() {
        return items.removeLast();
    }

    public T get(int index) {
        return items.get(index);
    }

    public T max() {
        return max(defaultComparator);
    }

    public T max(Comparator<T> c) {
        T max = null;
        Iterator<T> it = items.iterator();
        while (it.hasNext()) {
            T curr = it.next();
            if (max == null || c.compare(curr, max) > 0) {
                max = curr;
            }
        }
        return max;
    }
}
