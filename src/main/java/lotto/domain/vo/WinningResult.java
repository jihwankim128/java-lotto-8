package lotto.domain.vo;

import java.util.Map;

public record WinningResult(Map<Rank, Integer> result) {

    public WinningResult(Map<Rank, Integer> result) {
        this.result = Map.copyOf(result);
    }
}
