package lotto;

import lotto.application.LottoController;
import lotto.ui.ConsoleInputView;
import lotto.ui.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        new LottoController(new ConsoleInputView(), new ConsoleOutputView()).run();
    }
}
