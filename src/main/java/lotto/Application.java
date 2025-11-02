package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoMachine;
import lotto.external.RandomLottoGenerator;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoGenerator());
        new LottoController(new ConsoleInputView(), new ConsoleOutputView(), lottoMachine).run();
    }
}
