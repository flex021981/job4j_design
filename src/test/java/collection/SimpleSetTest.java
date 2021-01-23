package collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddThenIterate() {
        SimpleSet<Integer> subj = new SimpleSet<>();
        subj.add(null);
        subj.add(2);
        subj.add(3);
        subj.add(3);
        Iterator<Integer> it = subj.iterator();
        assertTrue(it.hasNext());
        assertThat(null, is(it.next()));
        assertTrue(it.hasNext());
        assertThat(2, is(it.next()));
        assertTrue(it.hasNext());
        assertThat(3, is(it.next()));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorError() {
        SimpleSet<Integer> subj = new SimpleSet<>();
        assertFalse(subj.iterator().hasNext());
        subj.iterator().next();
    }
}