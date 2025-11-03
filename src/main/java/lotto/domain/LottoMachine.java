package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.Rank;
import lotto.domain.vo.WinningNumbers;
import lotto.domain.vo.WinningResult;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1_000;

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

    public WinningResult analyzeWinningResult(Lottos purchaseLottos, WinningNumbers winningNumbers) {
        List<Rank> ranks = purchaseLottos.determineRanks(winningNumbers);
        Map<Rank, Integer> winningResult = ranks.stream()
                .collect(Collectors.toMap(rank -> rank, rank -> 1, Integer::sum));
        return new WinningResult(winningResult);
    }

    public double calculateProfitRatio(WinningResult winningResult) {
        long totalPrize = winningResult.calculateTotalPrize();
        return purchaseMoney.calculatePercentageOf(totalPrize);
    }
}
