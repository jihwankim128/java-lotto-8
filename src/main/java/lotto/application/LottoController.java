package lotto.application;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
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
        List<List<Integer>> lotteries = buyLotto();
        List<Integer> winningNumbers = consoleInputView.readWinningNumbers();
        int bonusNumber = consoleInputView.readBonusNumber();
        // TODO: 로또 당첨
        consoleOutputView.printWinningStatistics();
        consoleOutputView.printProfit();
    }

    private List<List<Integer>> buyLotto() {
        try {
            int money = consoleInputView.readPurchaseAmount();
            if (money % 1000 != 0) {
                throw new IllegalArgumentException();
            }
            List<List<Integer>> lotteries = new ArrayList<>();
            for (int i = 0; i < money / 1000; i++) {
                List<Integer> lotto = Randoms.pickUniqueNumbersInRange(1, 45, 6);
                lotteries.add(lotto);
            }
            consoleOutputView.printPurchaseResult(lotteries);
            return lotteries;
        } catch (IllegalArgumentException e) {
            consoleOutputView.printError(e.getMessage());
            return buyLotto();
        }
    }
}
