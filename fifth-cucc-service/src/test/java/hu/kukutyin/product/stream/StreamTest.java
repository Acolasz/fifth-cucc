package hu.kukutyin.product.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import org.hamcrest.Matchers;
import org.junit.Assert;

import hu.kukutyin.fifth.domain.Product;

public class StreamTest {
    private static final Product EMPTY_PRODUCT = null;
    private List<Product> productList;

    @Before
    public void init() {
        productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Federer");
        product1.setDescription("The BEST");
        productList.add(product1);
        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Ibrahimovic");
        product2.setDescription("The Best");
        productList.add(product2);
    }

    @Test
    public void getFilter_fromList_Test() {
        this.productList.stream()
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
        this.productList.stream()
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
        Product product = productList.stream()
                .filter(p -> 1 == p.getId())
                .findAny()
                .orElse(null);
        Assert.assertThat("findAny Product", product, Matchers.hasProperty("name", Matchers.equalTo("Federer")));
    }

    @Test
    public void update_fromList_Test() {
        Integer id = 1;
        Product product = new Product();
        product.setId(1);
        product.setName("Ibra");
        product.setDescription("The Best");
        productList = productList.stream()
                .map(p -> {
                    if (id.equals(p.getId())) {
                        return product;
                    } else {
                        return p;
                    }
                })
                .collect(Collectors.toList());
        Assert.assertThat("Update Product List", product, Matchers.hasProperty("name", Matchers.equalTo("Ibra")));
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
