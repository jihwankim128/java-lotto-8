package lotto.domain;

import java.util.List;

public class WinningLotto {

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호가 중복되었습니다.");
        }
        this.winningLotto = new Lotto(numbers);
        this.bonusNumber = bonusNumber;
    }

    public Rank determineRank(Lotto lotto) {
        long count = winningLotto.countMatching(lotto);
        boolean bonus = winningLotto.match(bonusNumber);
        return Rank.of((int) count, bonus);
    }
}
