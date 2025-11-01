package lotto.domain.vo;

import java.util.ArrayList;
import java.util.List;

public record Lottos(List<Lotto> lottos) {

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    @Override
    public List<Lotto> lottos() {
        return new ArrayList<>(lottos);
    }
}
