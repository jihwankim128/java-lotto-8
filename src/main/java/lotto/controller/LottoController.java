package lotto.controller;

import java.util.List;
import lotto.controller.dto.PurchaseDto;
import lotto.controller.dto.WinningStatisticsDto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoMachine;
import lotto.domain.WinningNumbers;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningResult;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class LottoController {

    private final ConsoleInputView inputView;
    private final ConsoleOutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoController(ConsoleInputView inputView, ConsoleOutputView outputView, LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        Money money = readMoney();
        LottoMachine lottoMachine = new LottoMachine(lottoGenerator, money);

        Lottos purchaseLottos = lottoMachine.issue();
        PurchaseDto purchaseDto = PurchaseDto.from(purchaseLottos);
        outputView.printPurchaseResult(purchaseDto);

        Lotto winningLotto = readWinningLotto();
        LottoNumber bonusNumber = readBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
        WinningResult winningResult = purchaseLottos.generateWinningResult(winningNumbers);
        double profitRatio = winningResult.calculateProfitRatio(money);
        WinningStatisticsDto winningStatisticsDto = WinningStatisticsDto.of(winningResult, profitRatio);

        outputView.printWinningStatistics(winningStatisticsDto);
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
