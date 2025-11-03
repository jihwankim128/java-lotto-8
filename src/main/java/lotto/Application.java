package lotto;

import lotto.controller.LottoController;
import lotto.external.RandomLottoGenerator;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        new LottoController(new ConsoleInputView(), new ConsoleOutputView(), new RandomLottoGenerator()).run();
    }
}
