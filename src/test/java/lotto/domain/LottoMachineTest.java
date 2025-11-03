package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.Rank;
import lotto.domain.vo.WinningNumbers;
import lotto.domain.vo.WinningResult;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    private final Lotto lotto = Lotto.generateOf(List.of(1, 2, 3, 4, 5, 6));
    private final LottoGenerator generator = () -> lotto;
    private final LottoMachine machine = new LottoMachine(generator);

    @Test
    void 금액이_주어지면_로또를_구매된다() {
        // given: 10000원이 주어졌을 때,
        Money purchaseMoney = new Money(10000);

        // when: 로또를 발행하면
        Lottos purchaseLottos = machine.issue(purchaseMoney);

        // then: 10장이 구매된다
        assertThat(purchaseLottos.lottos()).hasSize(10);
    }

    @Test
    void 로또_구매내역과_당첨번호로_당첨_내역을_분석한다() {
        // given: 발행한 로또 1개와 같은 당첨번호가 주어졌을 때,
        Lottos purchaseLottos = new Lottos(List.of(lotto));
        WinningNumbers winningNumbers = new WinningNumbers(lotto, new LottoNumber(10));

        // when: 당첨 결과를 분석하면
        WinningResult winningResult = machine.analyzeWinningResult(purchaseLottos, winningNumbers);

        // then: 1등이 1개다
        WinningResult expected = new WinningResult(Map.of(Rank.FIRST, 1));
        assertThat(winningResult).isEqualTo(expected);
    }

    @Test
    void 로또_당첨_결과로_총_수익율을_계산한다() {
        // given: 만 원이 주어지고 당첨 결과가 5만원 일 때,
        Money purchaseMoney = new Money(10000);
        WinningResult winningResult = new WinningResult(Map.of(Rank.FOURTH, 1));

        // when: 수익율을 계산한다면,
        double profitRatio = machine.calculateProfitRatio(winningResult, purchaseMoney);

        // then: 500배 수익 발생
        assertThat(profitRatio).isEqualTo(500);
    }
}