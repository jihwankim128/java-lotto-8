package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 로또_번호가_1부터_45가_아니라면_예외가_발생한다(int invalidLottoNumber) {
        // when & then
        assertThatThrownBy(() -> new LottoNumber(invalidLottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
