package lotto.domain.vo;

import java.util.List;

public record Lottos(List<Lotto> lottos) {

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public List<Rank> determineRanks(WinningNumbers winningNumbers) {
        return lottos.stream()
                .map(winningNumbers::determineRank)
                .filter(Rank::isWinning)
                .toList();
    }
}
