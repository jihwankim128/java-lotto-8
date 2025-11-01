package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.math.MathContext;
import lotto.domain.vo.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999})
    void 금액이_최소_금액보다_적다면_예외가_발생한다(int lessThanMinimumMoney) {
        // when & then
        assertThatThrownBy(() -> new Money(lessThanMinimumMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1001, 1999})
    void 천원_단위가_아니라면_예외가_발생한다(int invalidMoney) {
        // when & then
        assertThatThrownBy(() -> new Money(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class);
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


    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void 단위가격이_양수가_아니라면_예외가_발생한다() {
        // given
        int unitPrice = -1000;
        Money money = new Money(10000);

        // when & then
        assertThatThrownBy(() -> money.calculateQuantity(unitPrice))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비교_부분이_금액의_몇퍼센테이지인지_계산할_수_있다() {
        // given
        long part = 10000L;
        Money money = new Money(1000);

        // when
        double percentage = money.calculatePercentageOf(part);

        // then
        assertThat(percentage).isEqualTo(1000.0);
    }

    @Test
    void 최대_비교_부분과_최소_금액의_퍼센테이지_계산_정합도가_정확하다() {
        // given
        long part = Long.MAX_VALUE;
        Money money = new Money(1000);

        // when
        double percentage = money.calculatePercentageOf(part);

        // then
        BigDecimal expected = BigDecimal.valueOf(part)
                .divide(BigDecimal.valueOf(1000), MathContext.DECIMAL128)
                .multiply(BigDecimal.valueOf(100));
        assertThat(percentage).isEqualTo(expected.doubleValue());
    }
}