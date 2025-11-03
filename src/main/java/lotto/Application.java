package lotto;

import lotto.controller.LottoController;
import lotto.external.RandomLottoGenerator;
import lotto.ui.ConsoleInputHandler;
import lotto.ui.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        new LottoController(new ConsoleInputHandler(), new ConsoleOutputView(), new RandomLottoGenerator()).run();
    }
}
