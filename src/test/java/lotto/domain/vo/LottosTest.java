package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 로또_당첨번호로_당첨_순위를_생성_할_수_있다() {
        // given
        Lotto lotto = Lotto.generateOf(List.of(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(List.of(lotto));
        LottoNumber bonusNumber = new LottoNumber(10);
        WinningNumbers winningNumbers = new WinningNumbers(lotto, bonusNumber);

        // when
        List<Rank> ranks = lottos.determineRanks(winningNumbers);

        // then
        assertThat(ranks).containsOnly(Rank.FIRST);
    }

    @Test
    void 로또_당첨번호가_낙첨일_경우_결과에_포함되지_않는다() {
        // given
        Lotto lotto = Lotto.generateOf(List.of(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(List.of(lotto));

        Lotto winningLotto = Lotto.generateOf(List.of(7, 8, 9, 10, 11, 12));
        LottoNumber bonusNumber = new LottoNumber(13);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        // when
        List<Rank> ranks = lottos.determineRanks(winningNumbers);

        // then
        assertThat(ranks).hasSize(0);
    }
}