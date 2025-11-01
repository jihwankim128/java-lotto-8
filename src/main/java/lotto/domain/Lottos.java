package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(LottoGenerator lottoGenerator, int quantity) {
        this.lottos = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            List<Integer> numbers = lottoGenerator.generate();
            lottos.add(new Lotto(numbers));
        }
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}
