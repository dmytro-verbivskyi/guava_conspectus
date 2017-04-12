package cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dverbivskyi on 2017-04-12.
 */
public class _01_Cache {

    private static final long SECONDS = 3;

    @Test
    public void name() throws Exception {
        LoadingCache<String, List<Integer>> cachedNumbers = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(SECONDS, TimeUnit.SECONDS)
                //.removalListener(MY_LISTENER)
                .build(new CacheLoader<String, List<Integer>>() {
                    public List<Integer> load(String key) {
                        return createExpensiveGraph(key);
                    }
                });
        List<Integer> array = cachedNumbers.get("one");
        assertThat(array).containsExactly(1, 11, 21, 31);
    }

    private List<Integer> createExpensiveGraph(String key) {
        List<Integer> result = null;

        switch (key) {
            case "one":
                result = Lists.newArrayList(1, 11, 21, 31);
                break;
            case "two":
                result = Lists.newArrayList(2, 12, 22, 32);
                break;
            case "three":
                result = Lists.newArrayList(3, 13, 23, 33);
                break;
        }
        return result;
    }
}
