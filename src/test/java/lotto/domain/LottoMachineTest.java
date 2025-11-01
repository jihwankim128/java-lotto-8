package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    private final LottoGenerator generator = () -> new Lotto(List.of(1, 2, 3, 4, 5, 6));
    private final LottoMachine machine = new LottoMachine(generator);

    @Test
    void 금액이_주어지면_로또를_구매된다() {
        // given: 10000원이 주어졌을 때,
        Money money = new Money(10000);

        // when: 로또를 발행하면
        Lottos purchaseLottos = machine.issue(money);

        // then: 10장이 구매된다
        assertThat(purchaseLottos.lottos()).hasSize(10);
    }
}