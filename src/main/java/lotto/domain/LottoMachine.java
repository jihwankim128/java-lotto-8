package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;

    private final LottoGenerator generator;
    private final Money purchaseMoney;

    public LottoMachine(LottoGenerator generator, Money purchaseMoney) {
        this.generator = generator;
        this.purchaseMoney = purchaseMoney;
    }

    public Lottos issue() {
        int quantity = purchaseMoney.calculateQuantity(LOTTO_PRICE);

        List<Lotto> lottos = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            lottos.add(generator.generate());
        }

        return new Lottos(lottos);
    }
}
