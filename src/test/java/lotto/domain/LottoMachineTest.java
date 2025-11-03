package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.Rank;
import lotto.domain.vo.WinningResult;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    private final Lotto lotto = Lotto.generateOf(List.of(1, 2, 3, 4, 5, 6));
    private final LottoGenerator generator = () -> lotto;
    Money purchaseMoney = new Money(10000);

    @Test
    void 금액이_주어지면_로또를_구매된다() {
        // given: 10000원이 주어졌을 때,
        LottoMachine machine = new LottoMachine(generator, purchaseMoney);

        // when: 로또를 발행하면
        Lottos purchaseLottos = machine.issue();

        // then: 10장이 구매된다
        assertThat(purchaseLottos.lottos()).hasSize(10);
    }

    @Test
    void 로또_구매내역과_당첨번호로_당첨_내역을_분석한다() {
        // given: 10000원으로 발행한 로또와 당첨번호가 주어졌을 때,
        LottoMachine machine = new LottoMachine(generator, purchaseMoney);
        Lottos purchaseLottos = machine.issue();
        WinningNumbers winningNumbers = new WinningNumbers(lotto, new LottoNumber(10));

        // when: 당첨 결과를 분석하면
        WinningResult winningResult = machine.analyzeWinningResult(purchaseLottos, winningNumbers);

        // then: 1등이 10개다
        WinningResult expected = new WinningResult(Map.of(Rank.FIRST, 10));
        assertThat(winningResult).isEqualTo(expected);
    }

    @Test
    void 로또_당첨_결과로_총_수익율을_계산한다() {
        // given: 만 원이 주어지고 당첨 결과가 5만원 일 때,
        LottoMachine machine = new LottoMachine(generator, purchaseMoney);
        WinningResult winningResult = new WinningResult(Map.of(Rank.FOURTH, 1));

        // when: 수익율을 계산한다면,
        double profitRatio = machine.calculateProfitRatio(winningResult);

        // then: 500배 수익 발생
        assertThat(profitRatio).isEqualTo(500);
    }
}