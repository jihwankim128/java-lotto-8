package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    @Test
    void 당첨_금액으로_당첨_결과_수익률을_계산한다() {
        // given
        Map<Rank, Integer> result = Map.of(Rank.FIFTH, 1);
        WinningResult winningResult = new WinningResult(result);
        Money money = new Money(8000);

        // when
        double profitRatio = winningResult.calculateProfitRatio(money);

        // then
        assertThat(profitRatio).isEqualTo(62.5);
    }
}
