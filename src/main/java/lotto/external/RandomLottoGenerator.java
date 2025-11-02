package lotto.external;

import static lotto.domain.vo.Lotto.LOTTO_NUMBER_COUNT;
import static lotto.domain.vo.LottoNumber.MAX_RANGE;
import static lotto.domain.vo.LottoNumber.MIN_RANGE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.domain.LottoGenerator;
import lotto.domain.vo.Lotto;

public class RandomLottoGenerator implements LottoGenerator {

    @Override
    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MIN_RANGE, MAX_RANGE, LOTTO_NUMBER_COUNT);
        return Lotto.generateOf(numbers);
    }
}
