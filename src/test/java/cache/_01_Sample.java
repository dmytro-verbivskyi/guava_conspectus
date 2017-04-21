package cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.graph.Graph;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dverbivskyi on 2017-04-21.
 */
public class _01_Sample {

    private static final String ONE = "ONE";
    private static final String TWO = "TWO";
    private static final String THREE = "THREE";

    @Test
    public void first() throws Exception {
        LoadingCache<String, Category> cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(10, TimeUnit.MILLISECONDS)
//                .removalListener(MY_LISTENER)
                .build(
                        new CacheLoader<String, Category>() {
                            public Category load(String key) {
                                System.out.println(this);
                                return createExpensiveGraph(key);
                            }
                        });

        System.out.println(System.currentTimeMillis());
        assertThat(cache.get(ONE).getItems()).containsExactly("one", "two", "three");

        Thread.sleep(10);

        System.out.println(System.currentTimeMillis());
        assertThat(cache.get(TWO).getItems()).containsExactly("eleven", "twelve", "thirteen");

        System.out.println(System.currentTimeMillis());
        assertThat(cache.get(ONE).getItems()).containsExactly("one", "two", "three");

        System.out.println(System.currentTimeMillis());
        assertThat(cache.get(TWO).getItems()).containsExactly("eleven", "twelve", "thirteen");

        System.out.println(System.currentTimeMillis());
        assertThat(cache.get(ONE).getItems()).containsExactly("one", "two", "three");

        System.out.println(System.currentTimeMillis());
        assertThat(cache.get(TWO).getItems()).containsExactly("eleven", "twelve", "thirteen");
    }

    private Category createExpensiveGraph(String key) {
        System.out.println("Refreshing cache: " + key);
        Category category = new Category();

        switch (key) {
            case ONE:
                category.setItems(Arrays.asList("one", "two", "three"));
                break;
            case TWO:
                category.setItems(Arrays.asList("eleven", "twelve", "thirteen"));
                break;
            case THREE:
                category.setItems(Arrays.asList("twenty one", "twenty two", "twenty three"));
                break;
        }
        return category;
    }
}
