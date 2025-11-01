package lotto;

import lotto.application.LottoController;
import lotto.domain.LottoMachine;
import lotto.external.RandomLottoGenerator;
import lotto.ui.ConsoleInputView;
import lotto.ui.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoGenerator());
        new LottoController(new ConsoleInputView(), new ConsoleOutputView(), lottoMachine).run();
    }
}
