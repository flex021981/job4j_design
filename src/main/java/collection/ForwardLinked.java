package collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public T deleteFirst() {
        if (head != null && iterator().hasNext()) {
            T rsl = head.value;
            head = head.next;
            return rsl;
        } else {
            throw new NoSuchElementException();
        }
    }

    public T deleteLast() {
        Node<T> tail = head;
        Node<T> prev = tail;
        if (head.next == null){
            head = null;
            return tail.value;
        }
        if (head != null && iterator().hasNext()) {
            while (iterator().hasNext() && tail.next != null) {
                iterator().next();
                prev = tail;
                tail = tail.next;
            }
            prev.next = null;
            return tail.value;
        } else {
            throw new NoSuchElementException();
        }
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
