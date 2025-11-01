package lotto.application;

import java.util.List;
import lotto.domain.LottoMachine;
import lotto.domain.WinningNumbers;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
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
        LottoNumber bonusNumber = readBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
        WinningResult winningResult = purchaseLottos.generateWinningResult(winningNumbers);
        double profitRatio = winningResult.calculateProfitRatio(money);

        outputView.printWinningStatistics(winningResult.result());
        outputView.printProfitRate(profitRatio);
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

    private Lotto readWinningLotto() {
        try {
            List<Integer> winningNumbers = inputView.readWinningNumbers();
            return Lotto.generateOf(winningNumbers);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readWinningLotto();
        }
    }

    private LottoNumber readBonusNumber() {
        try {
            int bonusNumber = inputView.readBonusNumber();
            return new LottoNumber(bonusNumber);
        } catch (IllegalArgumentException e) {
            return readBonusNumber();
        }
    }
}
