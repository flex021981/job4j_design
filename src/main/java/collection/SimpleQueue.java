package collection;


import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (out.isEmpty()) {
            if (in.isEmpty()) {
                throw new NoSuchElementException();
            }
            while (!in.isEmpty()){
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }

    public void push(T value) {
        in.push(value);
    }
}
