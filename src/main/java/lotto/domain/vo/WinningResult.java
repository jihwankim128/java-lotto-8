package lotto.domain.vo;

import java.util.Map;

public record WinningResult(Map<Rank, Integer> result) {

    public WinningResult(Map<Rank, Integer> result) {
        this.result = Map.copyOf(result);
    }

    public long calculateTotalPrize() {
        return result.entrySet().stream()
                .mapToLong(this::calculatePrizeForEntry)
                .sum();
    }

    public int countByRank(Rank rank) {
        return result.getOrDefault(rank, 0);
    }

    private long calculatePrizeForEntry(Map.Entry<Rank, Integer> entry) {
        return entry.getKey()
                .calculateTotalPrize(entry.getValue());
    }
}
