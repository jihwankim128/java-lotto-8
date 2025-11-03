package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    @Test
    void 당첨_결과로_총_당첨_금액을_계산한다() {
        // given
        Map<Rank, Integer> result = Map.of(Rank.FIRST, 10);
        WinningResult winningResult = new WinningResult(result);

        // when
        long profitRatio = winningResult.calculateTotalPrize();

        // then
        long expected = Rank.FIRST.getPrize() * 10;
        assertThat(profitRatio).isEqualTo(expected);
    }
}
