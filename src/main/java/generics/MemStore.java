package generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();
    private int index;

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        T rsl = findById(id);
        if (rsl != null) {
            mem.add(index, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        T rsl = findById(id);
        if (rsl != null) {
            mem.remove(index - 1);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        index = 0;
        for (T element : mem) {
            index++;
            if (element.getId().equals(id)) {
                return element;
            }
        }
        return null;
    }
}
