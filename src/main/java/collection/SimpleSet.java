package collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable {
    private final SimpleArray<E> container;

    public SimpleSet() {
        this.container = new SimpleArray<>();
    }

    public void add(E element) {
        if (!contains(element)) {
            container.add(element);
        }
    }

    private boolean contains(E element) {
        Iterator<E> it = container.iterator();
        boolean rsl = false;
        while (it.hasNext()) {
            if (Objects.equals(element, it.next())) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator iterator() {
        return container.iterator();
    }
}
