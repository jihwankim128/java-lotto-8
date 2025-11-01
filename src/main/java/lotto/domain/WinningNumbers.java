package lotto.domain;

import lotto.domain.vo.Lotto;
import lotto.domain.vo.Rank;

public class WinningNumbers {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningNumbers(Lotto lotto, int bonusNumber) {
        if (lotto.match(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호가 중복되었습니다.");
        }
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank determineRank(Lotto other) {
        int count = lotto.countMatching(other);
        boolean bonus = lotto.match(bonusNumber);
        return Rank.of(count, bonus);
    }
}
