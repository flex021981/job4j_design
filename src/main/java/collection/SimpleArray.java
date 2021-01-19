package collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int index = 0;
    private int size = 1;
    private int modCount = 0;

    public SimpleArray() {
        this.container = new Object[size];
    }

    //добавляет указанный элемент (model) в первую свободную ячейку;
    public void add(T model) {
        modCount ++;
        if (index >= size) {
            size++;
            container = Arrays.copyOf(container, size);
        }
        container[index++] = model;
    }

    //возвращает элемент, расположенный по указанному индексу;
    public T get(int index) {
        Objects.checkIndex(index, this.index);
        return (T) container[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < index;
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[point++];
            }
        };
    }
}
