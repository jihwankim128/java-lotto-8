package lotto.controller;

import lotto.controller.dto.PurchaseDto;
import lotto.controller.dto.WinningStatisticsDto;
import lotto.controller.handler.InputHandler;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoMachine;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import lotto.domain.vo.WinningResult;
import lotto.ui.ConsoleOutputView;

public class LottoController {

    private final InputHandler inputHandler;
    private final ConsoleOutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoController(InputHandler inputHandler, ConsoleOutputView outputView, LottoGenerator lottoGenerator) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        Money money = inputHandler.readMoney();
        LottoMachine lottoMachine = new LottoMachine(lottoGenerator, money);

        Lottos purchaseLottos = lottoMachine.issue();
        PurchaseDto purchaseDto = PurchaseDto.from(purchaseLottos);
        outputView.printPurchaseResult(purchaseDto);

        WinningNumbers winningNumbers = inputHandler.readWinningNumbers();
        WinningResult winningResult = lottoMachine.analyzeWinningResult(purchaseLottos, winningNumbers);
        double profitRatio = lottoMachine.calculateProfitRatio(winningResult);
        WinningStatisticsDto winningStatisticsDto = WinningStatisticsDto.of(winningResult, profitRatio);
        outputView.printWinningStatistics(winningStatisticsDto);
    }
}
