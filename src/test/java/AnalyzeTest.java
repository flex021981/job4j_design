import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    private Analyze analyze = new Analyze();
    private Analyze.Info subj;
    private Analyze.Info rsl;
    private List<Analyze.User> previous;
    private List<Analyze.User> current;

    @Before
    public void setUp() throws Exception {
        previous = Arrays.asList(
                new Analyze.User(1, "Ivan"),
                new Analyze.User(2, "Nikolay"),
                new Analyze.User(3, "Stepan")
        );
    }

    @After
    public void tearDown() throws Exception {
        analyze = null;
        subj = null;
        rsl = null;
        previous = null;
        current = null;
    }

    @Test
    public void whenDiff() {
        current = Arrays.asList(
                new Analyze.User(1, "Ivan"),
                new Analyze.User(2, "Nikolay"),
                new Analyze.User(3, "Stepan")
        );
        subj = new Analyze.Info(0, 0, 0);
        rsl = analyze.diff(previous, current);
        assertThat(subj, is(rsl));
    }

    @Test
    public void whenAddAndDiff() {
        current = Arrays.asList(
                new Analyze.User(1, "Ivan"),
                new Analyze.User(2, "Nikolay"),
                new Analyze.User(3, "Stepan"),
                new Analyze.User(4, "Anton")
        );
        subj = new Analyze.Info(1, 0, 0);
        rsl = analyze.diff(previous, current);
        System.out.println(rsl.toString());
        assertThat(subj, is(rsl));
    }

    public void whenReplaceAndDiff() {
        current = Arrays.asList(
                new Analyze.User(1, "Ivan"),
                new Analyze.User(2, "Nikolay"),
                new Analyze.User(3, "StepanNew")
        );
        subj = new Analyze.Info(0, 1, 0);
        rsl = analyze.diff(previous, current);
        assertThat(subj, is(rsl));
    }

    @Test
    public void whenDeleteAndDiff() {
        current = Arrays.asList(
                new Analyze.User(1, "Ivan"),
                new Analyze.User(2, "Nikolay")
        );
        subj = new Analyze.Info(0, 0, 1);
        rsl = analyze.diff(previous, current);
        assertThat(subj, is(rsl));
    }
}