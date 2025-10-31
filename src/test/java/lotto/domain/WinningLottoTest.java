package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    void 로또_번호와_보너스_번호가_중복되면_예외가_발생한다() {
        // given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 6;

        // when & then
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_정보로_당첨_순위를_판별할_수_있다() {
        // given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        Rank rank = winningLotto.determineRank(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }
}