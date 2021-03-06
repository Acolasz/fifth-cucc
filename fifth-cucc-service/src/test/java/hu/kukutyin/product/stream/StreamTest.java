package hu.kukutyin.product.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.kukutyin.fifth.domain.Move;

public class StreamTest {
    private static final Move EMPTY_PRODUCT = null;
    private List<Move> moveList;

    @Before
    public void init() {
        moveList = new ArrayList<>();
        Move move1 = new Move();
        move1.setId(1);
        move1.setName("Federer");
        move1.setDescription("The BEST");
        moveList.add(move1);
        Move move2 = new Move();
        move2.setId(2);
        move2.setName("Ibrahimovic");
        move2.setDescription("The Best");
        moveList.add(move2);
    }

    @Test
    public void getFilter_fromList_Test() {
        this.moveList.stream()
                .filter(s -> {
//                    System.out.println("Nothing will write");
//                    System.out.println("------------------");
//                    System.out.println("filter: " + s);
//                    System.out.println("------------------");
                    return true;
                });
    }

    @Test
    public void getFilter_forEach_fromList_Test() {
        this.moveList.stream()
                .filter(s -> {
//                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> s.getId()
//                        System.out.println("forEach: " + s)
                );
    }

    @Test
    public void getFilter_findAny_fromList_Test() {
        Move move = moveList.stream()
                .filter(p -> 1 == p.getId())
                .findAny()
                .orElse(null);
        Assert.assertThat("findAny Move", move, Matchers.hasProperty("name", Matchers.equalTo("Federer")));
    }

    @Test
    public void update_fromList_Test() {
        Integer id = 1;
        Move move = new Move();
        move.setId(1);
        move.setName("Ibra");
        move.setDescription("The Best");
        moveList = moveList.stream()
                .map(p -> {
                    if (id.equals(p.getId())) {
                        return move;
                    } else {
                        return p;
                    }
                })
                .collect(Collectors.toList());
        Assert.assertThat("Update Move List", move, Matchers.hasProperty("name", Matchers.equalTo("Ibra")));
    }

    @Test
    public void collections() {
        final List<String> list = Arrays.asList("someValue", "anotherValue");

        // junit
        // java.lang.AssertionError: list.contains()
        Assert.assertTrue("list.contains()", list.contains("someValue") && list.contains("anotherValue"));

        // hamcrest
        // java.lang.AssertionError: list
        // Expected: (a collection containing "someValueX" and a collection containing "anotherValue")
        //      but: a collection containing "someValueX" was "someValue", was "anotherValue"
        Assert.assertThat("list", list, Matchers.hasItems("someValue")); // verify some of the elements
        Assert.assertThat("list", list, Matchers.contains("someValue", "anotherValue")); // exact match on elements in strict order
    }


    @Test
    public void property() {
        final Foo foo = new Foo("barValue");

        // junit
        // org.junit.ComparisonFailure: foo.getBar()
        // Expected :barValueX
        // Actual   :barValue
//        Assert.assertEquals("foo.getBar()","barValue", foo.getBar());

        // hamcrest
        // java.lang.AssertionError: foo
        // Expected: hasProperty("bar", "barValueX")
        //      but: property 'bar' was "barValue"
        Assert.assertThat("foo", foo, Matchers.hasProperty("bar", Matchers.equalTo("barValue")));
//        Assert.assertThat(foo, Matchers.hasProperty("bar", Matchers.equalTo("barValue")));
    }

    // TODO: non-public class/getter demo
    public static class Foo {
        private String bar;

        Foo(String bar) {
            this.bar = bar;
        }

        @SuppressWarnings("WeakerAccess")
        public String getBar() {
            return bar;
        }
    }
}
