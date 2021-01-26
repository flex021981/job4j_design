package map;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Iterable<SimpleMap.Node<K, V>> {
    private Node<K, V>[] table = new Node[16];
    private int size;
    private int modCount;
    private float loadFactor = 0.75f;
    private float threshold = table.length * loadFactor;

    private void hashing() {
        Node<K, V>[] oldTable = table;
        table = new Node[table.length * 2];
        threshold = table.length * loadFactor;
        for (Node<K, V> node : oldTable) {
            if (node != null) {
                table[getBucket(node.hash)] = node;
            }
        }
    }

    private int getBucket(int hash) {
        return (table.length - 1) & hash;
    }

    public boolean insert(K key, V value) {
        int hash = hash(key);
        int index = getBucket(hash);
        if (size + 1 > threshold) {
            hashing();
        }
        Node<K, V> newNode = new Node<>(key, value, hash);
        if (table[index] == null) {
            table[index] = newNode;
            size++;
            modCount++;
            return true;
        } else {
            if (key.equals(table[index].key)) {
                table[index] = newNode;
            }
        }
        return false;
    }

    public boolean delete(K key) {
        boolean rsl = false;
        int hash = hash(key);
        int index = getBucket(hash);
        Node<K, V> node = table[index];
        if (node != null && Objects.equals(key, node.key)) {
            table[index] = null;
            size--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    public V get(K key) {
        V value = null;
        int hash = hash(key);
        int index = getBucket(hash);
        Node<K, V> node = table[index];
        if (node != null && Objects.equals(key, node.key)) {
            value = node.value;
        }
        return value;
    }

    static final int hash(Object key) {
        int rsl;
        if (key == null) {
            rsl = 0;
        } else {
            int hashKey = key.hashCode();
            rsl = hashKey ^ (hashKey >>> 16);
        }
        return rsl;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                for (int i = index; i < table.length; i++) {
                    if (table[i] != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Node<K, V> next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++];
            }
        };
    }

    static class Node<K, V> {
        private final K key;
        private V value;
        private final int hash;

        Node(K key, V value, int hash) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return hash == node.hash
                    && Objects.equals(key, node.key)
                    && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, hash);
        }
    }
}
