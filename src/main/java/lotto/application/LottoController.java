package lotto.application;

import java.util.List;
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
        buyLotto();
        List<Integer> winningNumbers = consoleInputView.readWinningNumbers();
        int bonusNumber = consoleInputView.readBonusNumber();
        // TODO: 로또 당첨
        consoleOutputView.printWinningStatistics();
        consoleOutputView.printProfit();
    }

    private void buyLotto() {
        try {
            int money = consoleInputView.readPurchaseAmount();
            // TODO: 로또 구매
            consoleOutputView.printPurchaseResult();
        } catch (IllegalArgumentException e) {
            consoleOutputView.printError(e.getMessage());
            buyLotto();
        }
    }
}
