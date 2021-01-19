package generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (findById(id) != null) {
            mem.add(findIndex(id), model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (findById(id) != null) {
            mem.remove(findIndex(id));
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T element : mem) {
            if (element.getId().equals(id)) {
                return element;
            }
        }
        return null;
    }

    private int findIndex(String id) {
        int index = 0;
        for (T element : mem) {
            if (element.getId().equals(id)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
