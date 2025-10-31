package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 로또_생성기와_구매_수량_정보로_로또_목록을_생성한다() {
        // given
        LottoGenerator lottoGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        int quantity = 5;

        // when
        Lottos lottos = new Lottos(lottoGenerator, quantity);

        // then
        assertThat(lottos.getLottos()).hasSize(quantity);
    }
}