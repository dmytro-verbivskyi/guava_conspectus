package basic_util;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dverbivskyi on 2017-04-10.
 */
public class _03_Ordering {

    @Test
    public void notVerySophisticatedTest() throws Exception {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };
        List<String> list = Arrays.asList("aaaa", "aaa", "AA", "aaaaaAaa", "a");

        List<String> val = byLengthOrdering.greatestOf(list.iterator(), 2);
        assertThat(val).containsExactly("aaaaaAaa", "aaaa");
    }

}
