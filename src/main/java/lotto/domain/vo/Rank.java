package lotto.domain.vo;

import java.util.Arrays;

public enum Rank {

    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FOURTH(50_000, 4),
    FIFTH(5_000, 3),
    NONE(0, 0);

    private final long prize;
    private final int matchCount;

    Rank(long prize, int matchCount) {
        this.prize = prize;
        this.matchCount = matchCount;
    }

    public static Rank of(int matchCount, boolean bonus) {
        if (matchCount == SECOND.matchCount && bonus) {
            return SECOND;
        }
        return Arrays.stream(values())
                .filter(rank -> rank != SECOND && rank.matchCount == matchCount)
                .findFirst()
                .orElse(NONE);
    }

    public long getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isWinning() {
        return this != NONE;
    }

    public long calculateTotalPrize(int winningCount) {
        return prize * winningCount;
    }
}
