package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void 음수라면_예외가_발생한다() {
        // given
        int negativeMoney = -1;

        // when & then
        assertThatThrownBy(() -> new Money(negativeMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 음수일 수 없습니다.");
    }
}