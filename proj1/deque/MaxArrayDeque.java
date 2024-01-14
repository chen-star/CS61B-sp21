package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> defaultComparator;

    public MaxArrayDeque(Comparator<T> c) {
        defaultComparator = c;
    }

    public T max() {
        return max(defaultComparator);
    }

    public T max(Comparator<T> c) {
        T max = null;
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            T curr = it.next();
            if (max == null || c.compare(curr, max) > 0) {
                max = curr;
            }
        }
        return max;
    }
}
