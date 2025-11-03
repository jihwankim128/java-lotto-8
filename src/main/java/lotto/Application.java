package lotto;

import lotto.controller.InputHandler;
import lotto.controller.LottoController;
import lotto.controller.OutputPresenter;
import lotto.domain.LottoGenerator;
import lotto.external.RandomLottoGenerator;
import lotto.ui.ConsoleInputHandler;
import lotto.ui.ConsoleOutputPresenter;
import lotto.ui.view.ConsoleInputView;
import lotto.ui.view.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        ConsoleInputView inputView = new ConsoleInputView();
        ConsoleOutputView outputView = new ConsoleOutputView();

        InputHandler handler = new ConsoleInputHandler(inputView, outputView);
        OutputPresenter presenter = new ConsoleOutputPresenter(outputView);
        LottoGenerator generator = new RandomLottoGenerator();

        LottoController controller = new LottoController(handler, presenter, generator);
        controller.run();
    }
}
