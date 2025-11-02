package lotto.ui;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lotto.application.dto.LottoNumbersDto;
import lotto.application.dto.PurchaseDto;
import lotto.domain.vo.Rank;

public class ConsoleOutputView {

    public static final Collector<CharSequence, ?, String> BRACKET_JOINER = Collectors.joining(", ", "[", "]");

    private static String formatBracket(List<Integer> numbers) {
        return numbers.stream()
                .map(String::valueOf)
                .collect(BRACKET_JOINER);
    }

    public void printError(String message) {
        System.out.println("[ERROR]" + message);
    }

    public void printPurchaseResult(PurchaseDto purchase) {
        System.out.println();
        System.out.println(purchase.quantity() + "개를 구매했습니다.");
        for (LottoNumbersDto lottos : purchase.lottos()) {
            String formatted = formatBracket(lottos.numbers());
            System.out.println(formatted);
        }
        System.out.println();
    }

    public void printWinningStatistics(Map<Rank, Integer> winnings) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        for (Rank rank : Rank.values()) {
            int matchCount = rank.getMatchCount();
            long prize = rank.getPrize();
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
