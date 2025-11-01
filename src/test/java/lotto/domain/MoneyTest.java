package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.vo.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {999, 1001})
    void 천원_단위가_아니라면_예외가_발생한다(int invalidMoney) {
        // when & then
        assertThatThrownBy(() -> new Money(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 1000원 단위여야 합니다.");
    }

    @Test
    void 금액에서_단위_가격당_수량을_계산할_수_있다() {
        // given
        int unitPrice = 1000;
        Money money = new Money(10000);

        // when
        int count = money.calculateQuantity(unitPrice);

        // then
        assertThat(count).isEqualTo(10);
    }
}