package lotto.application;

import java.util.List;
import java.util.Map;
import lotto.domain.LottoMachine;
import lotto.domain.WinningNumbers;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.Rank;
import lotto.domain.vo.WinningResult;
import lotto.ui.ConsoleInputView;
import lotto.ui.ConsoleOutputView;

public class LottoController {

    private final ConsoleInputView inputView;
    private final ConsoleOutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoController(ConsoleInputView inputView, ConsoleOutputView outputView, LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void run() {
        Money money = readMoney();
        Lottos purchaseLottos = lottoMachine.issue(money);
        outputView.printPurchaseResult(purchaseLottos.lottos());

        Lotto winningLotto = readWinningLotto();
        int bonusNumber = readBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
        WinningResult winningResult = purchaseLottos.generateWinningResult(winningNumbers);
        double profit = calculateProfit(winningResult.result());

        outputView.printWinningStatistics(winningResult.result());
        outputView.printProfitRate(profit / money.money() * 100);
    }

    private int readBonusNumber() {
        try {
            return inputView.readBonusNumber();
        } catch (IllegalArgumentException e) {
            return readBonusNumber();
        }
    }

    private Lotto readWinningLotto() {
        try {
            List<Integer> winningNumbers = inputView.readWinningNumbers();
            return new Lotto(winningNumbers);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readWinningLotto();
        }
    }

    private Money readMoney() {
        try {
            int money = inputView.readPurchaseAmount();
            return new Money(money);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readMoney();
        }
    }

    private double calculateProfit(Map<Rank, Integer> ranks) {
        double profit = 0;
        for (Rank rank : ranks.keySet()) {
            profit += rank.getPrize() * ranks.get(rank);
        }
        return profit;
    }
}
