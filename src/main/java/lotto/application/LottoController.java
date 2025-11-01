package lotto.application;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.ui.ConsoleInputView;
import lotto.ui.ConsoleOutputView;

public class LottoController {

    private final ConsoleInputView consoleInputView;
    private final ConsoleOutputView consoleOutputView;

    public LottoController(ConsoleInputView consoleInputView, ConsoleOutputView consoleOutputView) {
        this.consoleInputView = consoleInputView;
        this.consoleOutputView = consoleOutputView;
    }

    public void run() {
        Money money = readMoney();
        Lottos purchaseLottos = buyLottos(money);
        consoleOutputView.printPurchaseResult(purchaseLottos.getLottos());

        Lotto winningLotto = readWinningLotto();
        int bonusNumber = readBonusNumber();
        WinningLotto winning = new WinningLotto(winningLotto, bonusNumber);
        Map<Rank, Integer> ranks = determineRank(purchaseLottos, winning);
        double profit = calculateProfit(ranks);

        consoleOutputView.printWinningStatistics(ranks);
        consoleOutputView.printProfitRate(profit / money.getMoney() * 100);
    }

    private int readBonusNumber() {
        try {
            return consoleInputView.readBonusNumber();
        } catch (IllegalArgumentException e) {
            return readBonusNumber();
        }
    }

    private Lotto readWinningLotto() {
        try {
            List<Integer> winningNumbers = consoleInputView.readWinningNumbers();
            return new Lotto(winningNumbers);
        } catch (IllegalArgumentException e) {
            consoleOutputView.printError(e.getMessage());
            return readWinningLotto();
        }
    }

    private Money readMoney() {
        try {
            int money = consoleInputView.readPurchaseAmount();
            return new Money(money);
        } catch (IllegalArgumentException e) {
            consoleOutputView.printError(e.getMessage());
            return readMoney();
        }
    }

    private Map<Rank, Integer> determineRank(Lottos lottos, WinningLotto winning) {
        return lottos.getLottos().stream()
                .map(winning::determineRank)
                .filter(rank -> rank != Rank.NONE)
                .collect(Collectors.toMap(rank -> rank, rank -> 1, Integer::sum));
    }

    private Lottos buyLottos(Money money) {
        LottoGenerator generator = () -> Randoms.pickUniqueNumbersInRange(1, 45, 6);
        int quantity = money.calculateQuantity(1000);
        return new Lottos(generator, quantity);
    }

    private double calculateProfit(Map<Rank, Integer> ranks) {
        double profit = 0;
        for (Rank rank : ranks.keySet()) {
            profit += rank.getPrize() * ranks.get(rank);
        }
        return profit;
    }
}
