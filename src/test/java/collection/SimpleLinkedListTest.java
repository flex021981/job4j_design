package collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {
    SimpleLinkedList<Integer> subj;
    Iterator<Integer> it;

    @Before
    public void setUp() throws Exception {
        subj = new SimpleLinkedList<>();
    }

    @Test
    public void whenAddAndGet() {
        int size = 10;
        for (int i = 0; i < size; i++) {
            subj.add(i);
        }
        for (int i = 0; i < size; i++) {
            assertThat(i, is(subj.get(i)));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetError() {
        int size = 5;
        for (int i = 0; i < size; i++) {
            subj.add(i);
        }
        subj.get(size + 1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyError() {
        assertFalse(subj.iterator().hasNext());
        subj.iterator().next();
    }
    @Test(expected = ConcurrentModificationException.class)
    public void whenGetModifcationError() {
        int size = 10;
        for (int i = 0; i < size; i++) {
            subj.add(i);
        }
        it = subj.iterator();
        subj.add(50);

        assertTrue(subj.iterator().hasNext());
        assertTrue(it.hasNext());
        it.next();
    }
}