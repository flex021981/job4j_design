package iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.addAfter(input, 1, 3);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 2, 2);
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4, 3));
        List<Integer> rsl = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.removeIf(input, e -> e % 2 == 0);
        assertThat(rsl, Is.is(input));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> rsl = new ArrayList<>(Arrays.asList(1, 2, 0, 0));
        ListUtils.replaceIf(input, e -> e >= 3, 0);
        assertThat(rsl, Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> inputOne = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> inputTwo = new ArrayList<>(Arrays.asList(2, 4));
        List<Integer> rsl = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.removeAll(inputOne, inputTwo);
        assertThat(rsl, Is.is(inputOne));
    }
}