package basic_util;

import com.google.common.base.Optional;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class _01_Optional {

    @Test
    public void notNullIntoOf() throws Exception {
        Integer input = 5;
        Optional<Integer> possible = Optional.of(input);

        assertThat(possible.isPresent()).isTrue();
        assertThat(possible.get()).isEqualTo(5);
    }

    @Test(expected = NullPointerException.class)
    public void nullIntoOfMustThrowNullPointerException() throws Exception {
        Integer input = null;
        Optional.of(input);
    }

    @Test
    public void notNullFromNullable() throws Exception {
        Integer input = 5;
        Optional<Integer> possible = Optional.fromNullable(input);

        assertThat(possible.isPresent()).isTrue();
        assertThat(possible.or(57)).isEqualTo(5);
    }

    @Test
    public void nullRefWorkingAroundUseOrToProvideDefaultValue() throws Exception {
        Integer input = null;
        Optional<Integer> possible = Optional.fromNullable(input);

        assertThat(possible.isPresent()).isFalse();
        assertThat(possible.or(57)).isEqualTo(57);
    }


}
