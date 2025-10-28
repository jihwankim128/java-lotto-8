package lotto.ui;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import lotto.domain.Winning;

public class ConsoleOutputView {

    public void printError(String message) {
        System.out.println(message);
    }

    public void printPurchaseResult(List<List<Integer>> lotteries) {
        System.out.println();
        System.out.println(lotteries.size() + "개를 구매했습니다.");
        for (List<Integer> lotto : lotteries) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int number : lotto) {
                sb.append(number).append(", ");
            }
            sb.deleteCharAt(sb.lastIndexOf(", "));
            sb.append("]");
            System.out.println(sb);
        }
        System.out.println("구매 출력입니다");
    }

    public void printWinningStatistics(Map<Winning, Integer> winnings) {
        System.out.println("당첨 통계");
        System.out.println("---");
        for (Winning winning : Winning.values()) {
            if (winning == Winning.GROUND) {
                continue;
            }
            int correctCount = winning.getCorrectCount();
            int amount = winning.getAmount();
            String amountFormat = NumberFormat.getInstance().format(amount);

            int winningCount = winnings.getOrDefault(winning, 0);
            if (Winning.SECOND == winning) {
                System.out.println(correctCount + "개 일치, 보너스 볼 일치 (" + amountFormat + ") - " + winningCount + "개");
            }
            System.out.println(correctCount + "개 일치 (" + amountFormat + ") - " + winningCount + "개");
        }
    }

    public void printProfit() {
        System.out.println("총 수익율 출력입니다.");
    }
}
