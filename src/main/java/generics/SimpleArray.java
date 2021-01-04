package generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable {
    private final Object[] array;
    private int index = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    //добавляет указанный элемент (model) в первую свободную ячейку;
    public void add(T model) {
        array[index++] = model;
    }

    //заменяет указанным элементом (model) элемент, находящийся по индексу index;
    public void set(int index, T model) {
        Objects.checkIndex(index, this.index);
        array[index] = model;
    }

    //удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо
// сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек);
    public void remove(int index) {
        Objects.checkIndex(index, this.index);
        array[index] = null;
        this.index--;
        System.arraycopy(array, index + 1, array, index, this.index - index);
    }

    //возвращает элемент, расположенный по указанному индексу;
    public T get(int index) {
        Objects.checkIndex(index, this.index);
        return (T) array[index];
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < index;
            }

            @Override
            public Object next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[point++];
            }
        };
    }
}
