package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lotto.domain.vo.Lotto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    static Stream<Arguments> invalidLottoNumberRangeCases() {
        return Stream.of(
                Arguments.of(List.of(0, 1, 11, 21, 31, 41)),
                Arguments.of(List.of(1, 11, 21, 31, 41, 46))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 7})
    void 로또_번호의_개수가_6개가_아니라면_예외가_발생한다(int invalidLottoCount) {
        // given
        List<Integer> lottoNumbers = IntStream.range(1, invalidLottoCount + 1).boxed().toList();

        // when & then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("invalidLottoNumberRangeCases")
    void 로또_번호가_1부터_45가_아니라면_예외가_발생한다(List<Integer> lottoNumbers) {
        // when & then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({"1, true", "0, false"})
    void 로또에_포함된_번호인지_알_수_있다(int number, boolean expected) {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        boolean match = lotto.match(number);

        // then
        assertThat(match).isEqualTo(expected);
    }

    @Test
    void 다른_로또의_번호가_몇_개_포함되었는지_알_수_있다() {
        // given
        Lotto baseLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto otherLotto = new Lotto(List.of(1, 2, 3, 4, 7, 8));

        // when
        long count = baseLotto.countMatching(otherLotto);

        // then
        assertThat(count).isEqualTo(4);
    }
}
