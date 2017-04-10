package basic_util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.google.common.base.Preconditions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class _02_Preconditions {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void nullWithCheckNotNull() throws Exception {
        expected.expect(NullPointerException.class);
        expected.expectMessage("This is error [template] for NullPointerException [15, text]");

        checkNotNull(null, "This is error [%s] for NullPointerException", "template", 15, "text");
    }

    @Test
    public void notNullWithCheckNotNull() throws Exception {
        Integer input = 13;
        checkNotNull(input, "This is error [%s] for NullPointerException", "template", 15, "text");
    }

    @Test
    public void nullWithCheckNotNullArgumentMightBeNull() throws Exception {
        expected.expect(NullPointerException.class);
        expected.expectMessage("Error might contain even [null] arguments here [null, text]");

        checkNotNull(null, "Error might contain even [%s] arguments here", null, null, "text");
    }

    @Test
    public void nullWithCheckNotNullAbsentErrorMessageTemplate() throws Exception {
        try {
            checkNotNull(null);
        } catch (Exception e) {
            assertThat(e.getClass()).isEqualTo(NullPointerException.class);
            assertThat(e.getMessage()).isNull();
        }
    }

    @Test
    public void checkArgumentTest() throws Exception {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Input must be less then [10], but was [11]");

        Integer input = 11;
        checkArgument(input < 10, "Input must be less then [%s], but was [%s]", 10, input);
    }

    @Test
    public void checkStateTest() throws Exception {
        expected.expect(IllegalStateException.class);
        expected.expectMessage("Input must be less then [10], but was [11]");

        Integer input = 11;
        checkState(input < 10, "Input must be less then [%s], but was [%s]", 10, input);
    }

    @Test
    public void checkElementIndexTest() throws Exception {
        expected.expect(IndexOutOfBoundsException.class);
        expected.expectMessage("This is just prefix for exception (16) must be less than size (10)");

        checkElementIndex(16, 10, "This is just prefix for exception");
    }

    @Test
    public void checkElementIndexNoPrefixHere() throws Exception {
        expected.expect(IndexOutOfBoundsException.class);
        expected.expectMessage("index (16) must be less than size (10)");

        checkElementIndex(16, 10);
    }
}
