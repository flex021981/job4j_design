package tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(1, 4));
        assertTrue(tree.add(4, 5));
        assertTrue(tree.add(5, 6));
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        assertTrue(tree.add(1, 2));
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenTreeIsBinary() {
        Tree<Integer> tree = new Tree<>(1);
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(2, 4));
        assertTrue(tree.add(2, 5));
        assertTrue(tree.add(3, 6));
        assertTrue(tree.add(3, 7));
        assertTrue(tree.isBinary());
    }

    @Test
    public void whenTreeIsNotBinary() {
        Tree<Integer> tree = new Tree<>(1);
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(1, 4));
        assertTrue(tree.add(2, 5));
        assertTrue(tree.add(3, 6));
        assertTrue(tree.add(3, 7));
        assertFalse(tree.isBinary());
    }
}