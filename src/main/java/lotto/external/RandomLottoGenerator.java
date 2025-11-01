package lotto.external;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.domain.LottoGenerator;
import lotto.domain.vo.Lotto;

public class RandomLottoGenerator implements LottoGenerator {

    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 45;
    private static final int PICK_COUNT = 6;

    @Override
    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MIN_RANGE, MAX_RANGE, PICK_COUNT);
        return new Lotto(numbers);
    }
}
