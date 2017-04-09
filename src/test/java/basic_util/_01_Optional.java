package basic_util;

import com.google.common.base.Optional;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class _01_Optional {

    @Test
    public void happyOptional() throws Exception {
        Optional<Integer> possible = Optional.of(5);

        assertThat(possible.isPresent()).isTrue();
        assertThat(possible.get()).isNotNull();
        assertThat(possible.get()).isEqualTo(5);
    }
}
