package deque;

public class LinkedListDeque<T> {

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

    public void addFirst(T item) {
        Node<T> toAdd = new Node<>(item);
        toAdd.next = head.next;
        toAdd.next.prev = toAdd;
        toAdd.prev = head;
        head.next = toAdd;
        size++;
    }

    public void addLast(T item) {
        Node<T> toAdd = new Node<>(item);
        toAdd.prev = tail.prev;
        toAdd.prev.next = toAdd;
        toAdd.next = tail;
        tail.prev = toAdd;
        size++;
    }

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

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

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

        public Node(T data) {
            this.data = data;
        }
    }
}
