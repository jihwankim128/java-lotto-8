package lotto.domain;

import java.util.List;

public class WinningLotto {

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호가 중복되었습니다.");
        }
    }
}
