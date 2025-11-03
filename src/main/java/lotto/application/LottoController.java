package lotto.application;

import lotto.domain.LottoGenerator;
import lotto.domain.LottoMachine;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import lotto.domain.vo.WinningResult;

public class LottoController {

    private final InputHandler inputHandler;
    private final OutputPresenter presenter;
    private final LottoGenerator lottoGenerator;

    public LottoController(InputHandler inputHandler, OutputPresenter presenter, LottoGenerator lottoGenerator) {
        this.inputHandler = inputHandler;
        this.presenter = presenter;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        Money money = inputHandler.readMoney();
        LottoMachine lottoMachine = new LottoMachine(lottoGenerator);
        Lottos purchaseLottos = lottoMachine.issue(money);
        presenter.presentPurchase(purchaseLottos);

        WinningNumbers winningNumbers = inputHandler.readWinningNumbers();
        WinningResult winningResult = lottoMachine.analyzeWinningResult(purchaseLottos, winningNumbers);
        double profitRatio = lottoMachine.calculateProfitRatio(winningResult, money);
        presenter.presentResult(winningResult, profitRatio);
    }
}
