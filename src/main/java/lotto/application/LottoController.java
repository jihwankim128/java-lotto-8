package lotto.application;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Winning;
import lotto.ui.ConsoleInputView;
import lotto.ui.ConsoleOutputView;

public class LottoController {

    private final ConsoleInputView consoleInputView;
    private final ConsoleOutputView consoleOutputView;

    public LottoController(ConsoleInputView consoleInputView, ConsoleOutputView consoleOutputView) {
        this.consoleInputView = consoleInputView;
        this.consoleOutputView = consoleOutputView;
    }

    private static double calculateProfit(Map<Winning, Integer> winnings) {
        double profit = 0;
        for (Winning winning : winnings.keySet()) {
            profit += winning.getAmount() * winnings.get(winning);
        }
        return profit;
    }

    public void run() {
        int money = readMoney();
        List<List<Integer>> lotteries = buyLotto(money);
        consoleOutputView.printPurchaseResult(lotteries);

        Map<Winning, Integer> winnings = determineWinning(lotteries);
        double profit = calculateProfit(winnings);

        consoleOutputView.printWinningStatistics(winnings);
        consoleOutputView.printProfitRate(profit / money);
    }

    private int readMoney() {
        try {
            int money = consoleInputView.readPurchaseAmount();
            if (money % 1000 != 0) {
                throw new IllegalArgumentException();
            }
            return money;
        } catch (IllegalArgumentException e) {
            consoleOutputView.printError(e.getMessage());
            return readMoney();
        }
    }

    private Map<Winning, Integer> determineWinning(List<List<Integer>> lotteries) {
        List<Integer> winningNumbers = consoleInputView.readWinningNumbers();
        int bonusNumber = consoleInputView.readBonusNumber();

        Map<Winning, Integer> winnings = new HashMap<>();
        Lotto lotto = new Lotto(winningNumbers);

        for (List<Integer> purchaseLotto : lotteries) {
            int correctCount = lotto.countWinning(new Lotto(purchaseLotto));
            boolean bonus = lotto.isWinning(bonusNumber);

            Winning winning = Winning.of(correctCount, bonus);
            winnings.put(winning, winnings.getOrDefault(winning, 0) + 1);
        }
        return winnings;
    }

    private List<List<Integer>> buyLotto(int money) {
        List<List<Integer>> lotteries = new ArrayList<>();
        for (int i = 0; i < money / 1000; i++) {
            List<Integer> lotto = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lotteries.add(lotto);
        }
        return lotteries;
    }
}
