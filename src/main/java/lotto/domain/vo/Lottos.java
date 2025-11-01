package lotto.domain.vo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.WinningNumbers;

public record Lottos(List<Lotto> lottos) {

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public WinningResult generateWinningResult(WinningNumbers winningNumbers) {
        Map<Rank, Integer> matchResult = lottos.stream()
                .map(winningNumbers::determineRank)
                .filter(Rank::isWinning)
                .collect(Collectors.toMap(rank -> rank, rank -> 1, Integer::sum));
        return new WinningResult(matchResult);
    }
}
