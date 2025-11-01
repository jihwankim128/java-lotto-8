package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Rank;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @Test
    void 로또_번호와_보너스_번호가_중복되면_예외가_발생한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 6;

        // when & then
        assertThatThrownBy(() -> new WinningNumbers(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_정보로_당첨_순위를_판별할_수_있다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningNumbers winningNumbers = new WinningNumbers(lotto, bonusNumber);
        Lotto other = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        Rank rank = winningNumbers.determineRank(other);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }
}