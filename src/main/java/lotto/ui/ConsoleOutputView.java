package lotto.ui;

import java.util.List;

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

    public void printWinningStatistics() {
        System.out.println("당첨 통계");
        System.out.println("---");
        // TODO: 통계 출력
    }

    public void printProfit() {
        System.out.println("총 수익율 출력입니다.");
    }
}
