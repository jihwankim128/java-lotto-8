package lotto.application.dto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import lotto.domain.vo.Rank;
import lotto.domain.vo.WinningResult;

public record WinningStatisticsDto(List<WinningDto> winningResult, double profitRatio) {

    public static WinningStatisticsDto of(WinningResult winningResult, double profitRatio) {
        List<WinningDto> winnings = Arrays.stream(Rank.values())
                .filter(Rank::isWinning)
                .sorted(Comparator.reverseOrder())
                .map(rank -> WinningDto.of(rank, winningResult.countByRank(rank)))
                .toList();
        return new WinningStatisticsDto(winnings, profitRatio);
    }
}
