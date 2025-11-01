package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 로또_당첨번호로_당첨_결과를_조회_할_수_있다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(List.of(lotto));
        WinningNumbers winningNumbers = new WinningNumbers(lotto, 10);

        // when
        WinningResult winningResult = lottos.generateWinningResult(winningNumbers);

        // then
        WinningResult expected = new WinningResult(Map.of(Rank.FIRST, 1));
        assertThat(winningResult).isEqualTo(expected);
    }
}