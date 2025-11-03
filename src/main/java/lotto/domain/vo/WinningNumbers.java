package lotto.domain.vo;

public class WinningNumbers {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningNumbers(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.match(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호가 중복되었습니다.");
        }
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank determineRank(Lotto other) {
        int count = other.countMatching(lotto);
        boolean bonus = other.match(bonusNumber);
        return Rank.of(count, bonus);
    }
}
