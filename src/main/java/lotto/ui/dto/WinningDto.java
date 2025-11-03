package lotto.ui.dto;

import lotto.domain.vo.Rank;

public record WinningDto(
        int matchCount,
        boolean second,
        long prize,
        int count
) {

    public static WinningDto of(Rank rank, int count) {
        return new WinningDto(
                rank.getMatchCount(),
                rank == Rank.SECOND,
                rank.getPrize(),
                count
        );
    }
}
