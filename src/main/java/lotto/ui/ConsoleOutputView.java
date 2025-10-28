package lotto.ui;

public class ConsoleOutputView {

    public void printError(String message) {
        System.out.println(message);
    }

    public void printPurchaseResult() {
        System.out.println();
        // TODO: 구매 출력
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
