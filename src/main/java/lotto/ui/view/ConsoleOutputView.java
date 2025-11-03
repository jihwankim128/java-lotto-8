package lotto.ui.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lotto.ui.dto.LottoNumbersDto;
import lotto.ui.dto.PurchaseDto;
import lotto.ui.dto.WinningDto;
import lotto.ui.dto.WinningStatisticsDto;

public class ConsoleOutputView {

    private static final Collector<CharSequence, ?, String> BRACKET_JOINER = Collectors.joining(", ", "[", "]");
    private static final NumberFormat PRIZE_FORMATTER = NumberFormat.getInstance();
    private static final DecimalFormat RATIO_FORMATTER = new DecimalFormat("#,##0.0");

    private static String formatBracket(List<Integer> numbers) {
        return numbers.stream()
                .map(String::valueOf)
                .collect(BRACKET_JOINER);
    }

    private static String getWinningFormat(boolean isSecond) {
        if (isSecond) {
            return "%d개 일치, 보너스 볼 일치 (%s원) - %d개%n";
        }
        return "%d개 일치 (%s원) - %d개%n";
    }

    private static void printWinningRow(WinningDto winning) {
        String formattedPrize = PRIZE_FORMATTER.format(winning.prize());
        String winningFormat = getWinningFormat(winning.second());
        System.out.printf(winningFormat, winning.matchCount(), formattedPrize, winning.count());
    }

    public void printError(String message) {
        System.out.println("[ERROR] " + message);
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

    public void printWinningStatistics(WinningStatisticsDto winningStatistics) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        winningStatistics.winningResult()
                .forEach(ConsoleOutputView::printWinningRow);

        String formattedProfitRatio = RATIO_FORMATTER.format(winningStatistics.profitRatio());
        System.out.printf("총 수익률은 %s%%입니다.%n", formattedProfitRatio);
    }
}
