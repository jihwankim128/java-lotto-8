package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    @ParameterizedTest
    @ValueSource(ints = {5, 7})
    void 로또_번호의_개수가_6개가_아니라면_예외가_발생한다(int invalidLottoCount) {
        // given
        List<Integer> lottoNumbers = IntStream.range(1, invalidLottoCount + 1).boxed().toList();

        // when & then
        assertThatThrownBy(() -> Lotto.generateOf(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> Lotto.generateOf(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({"1, true", "10, false"})
    void 로또에_포함된_번호인지_알_수_있다(int number, boolean expected) {
        // given
        Lotto lotto = Lotto.generateOf(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = new LottoNumber(number);

        // when
        boolean match = lotto.match(lottoNumber);

        // then
        assertThat(match).isEqualTo(expected);
    }

    @Test
    void 다른_로또의_번호가_몇_개_포함되었는지_알_수_있다() {
        // given
        Lotto baseLotto = Lotto.generateOf(List.of(1, 2, 3, 4, 5, 6));
        Lotto otherLotto = Lotto.generateOf(List.of(1, 2, 3, 4, 7, 8));

        // when
        long count = baseLotto.countMatching(otherLotto);

        // then
        assertThat(count).isEqualTo(4);
    }
}
