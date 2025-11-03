package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(TestHelper.generateOutputData());
                },
                TestHelper.generateRandomNumber(),
                TestHelper.generateRandomNumbers()
        );
    }

    @Test
    void 구매금액_재입력_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("1234", "8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(TestHelper.generateOutputData());
                },
                TestHelper.generateRandomNumber(),
                TestHelper.generateRandomNumbers()
        );
    }

    @Test
    void 당첨번호_재입력_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "1", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(TestHelper.generateOutputData());
                },
                TestHelper.generateRandomNumber(),
                TestHelper.generateRandomNumbers()
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
