package map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    private SimpleMap<Integer, String> subj;

    @Before
    public void setUp() throws Exception {
        subj = new SimpleMap<>();
    }

    @After
    public void tearDown() throws Exception {
        subj = null;
    }

    @Test
    public void whenInsertAndGet() {
        assertTrue(subj.insert(12, "Node"));
        assertFalse(subj.insert(12, "Node"));
        assertThat(subj.get(12), is("Node"));
    }

    @Test
    public void whenInsertAndDelete() {
        assertTrue(subj.insert(1, "Node-1"));
        assertTrue(subj.insert(2, "Node-2"));
        assertThat(subj.get(1), is("Node-1"));
        assertTrue(subj.delete(2));
        assertNull(subj.get(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenInsertAndIterate() {
        assertTrue(subj.insert(1, "Node-1"));
        assertTrue(subj.insert(2, "Node-2"));
        Iterator<SimpleMap.Node<Integer, String>> it = subj.iterator();
        while (it.hasNext()) {
            it.next();
            subj.insert(3, "Node-3");
        }
    }
}