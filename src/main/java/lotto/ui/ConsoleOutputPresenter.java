package lotto.ui;

import lotto.controller.OutputPresenter;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.WinningResult;
import lotto.ui.dto.PurchaseDto;
import lotto.ui.dto.WinningStatisticsDto;
import lotto.ui.view.ConsoleOutputView;

public class ConsoleOutputPresenter implements OutputPresenter {

    private final ConsoleOutputView outputView;

    public ConsoleOutputPresenter(ConsoleOutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public void presentPurchase(Lottos lottos) {
        PurchaseDto dto = PurchaseDto.from(lottos);
        outputView.printPurchaseResult(dto);
    }

    @Override
    public void presentResult(WinningResult result, double profitRatio) {
        WinningStatisticsDto dto = WinningStatisticsDto.of(result, profitRatio);
        outputView.printWinningStatistics(dto);
    }
}
