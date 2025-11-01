package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.vo.Rank;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",  // 2등만 보너스를 확인
            "5, false, THIRD",
            "4, false, FOURTH",
            "3, false, FIFTH",
            "2, false, NONE",
            "1, false, NONE",
            "0, false, NONE"
    })
    void 로또가_일치한_개수와_보너스_여부로_순위를_판별한다(int matchCount, boolean bonus, Rank expected) {
        // when
        Rank rank = Rank.of(matchCount, bonus);

        // then
        assertThat(rank).isEqualTo(expected);
    }
}