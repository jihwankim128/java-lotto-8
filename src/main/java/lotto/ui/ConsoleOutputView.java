package lotto.ui;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Rank;

public class ConsoleOutputView {

    public void printError(String message) {
        System.out.println("[ERROR]" + message);
    }

    public void printPurchaseResult(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int number : lotto.numbers()) {
                sb.append(number).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append("]");
            System.out.println(sb);
        }
        System.out.println();
    }

    public void printWinningStatistics(Map<Rank, Integer> winnings) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        for (Rank rank : Rank.values()) {
            int matchCount = rank.getMatchCount();
            int prize = rank.getPrize();
            String amountFormat = NumberFormat.getInstance().format(prize);

            int winningCount = winnings.getOrDefault(rank, 0);
            if (Rank.SECOND == rank) {
                System.out.println(matchCount + "개 일치, 보너스 볼 일치 (" + amountFormat + "원) - " + winningCount + "개");
                continue;
            }
            System.out.println(matchCount + "개 일치 (" + amountFormat + "원) - " + winningCount + "개");
        }
    }

    public void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + String.format("%.1f", profitRate) + "%입니다.");
    }
}
