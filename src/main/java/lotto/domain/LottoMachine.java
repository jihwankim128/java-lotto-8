package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;

    private final LottoGenerator lottoGenerator;

    public LottoMachine(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public Lottos issue(Money money) {
        int quantity = money.calculateQuantity(LOTTO_PRICE);
        List<Lotto> lottos = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            lottos.add(lottoGenerator.generate());
        }
        return new Lottos(lottos);
    }
}
