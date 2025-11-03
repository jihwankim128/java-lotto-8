package lotto.ui.dto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import lotto.domain.vo.Rank;
import lotto.domain.vo.WinningResult;

public record WinningStatisticsDto(List<WinningDto> winningResult, double profitRatio) {

    public static WinningStatisticsDto of(WinningResult winningResult, double profitRatio) {
        List<WinningDto> winnings = Arrays.stream(Rank.values())
                .filter(Rank::isWinning)
                .sorted(getComparator())
                .map(rank -> WinningDto.of(rank, winningResult.countByRank(rank)))
                .toList();
        return new WinningStatisticsDto(winnings, profitRatio);
    }

    private static Comparator<Rank> getComparator() {
        return Comparator.comparingInt(Rank::getMatchCount)
                .thenComparing(Comparator.reverseOrder());
    }
}
