package lotto.ui;

import java.util.List;
import java.util.function.Supplier;
import lotto.application.InputHandler;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import lotto.ui.view.ConsoleInputView;
import lotto.ui.view.ConsoleOutputView;

public class ConsoleInputHandler implements InputHandler {

    private final ConsoleInputView inputView;
    private final ConsoleOutputView outputView;

    public ConsoleInputHandler(ConsoleInputView inputView, ConsoleOutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public Money readMoney() {
        return retryOnError(() -> {
            int money = inputView.readPurchaseAmount();
            return new Money(money);
        });
    }

    @Override
    public WinningNumbers readWinningNumbers() {
        return retryOnError(() -> {
            List<Integer> winningNumbers = inputView.readWinningNumbers();
            int bonusNumber = inputView.readBonusNumber();
            return new WinningNumbers(
                    Lotto.generateOf(winningNumbers),
                    new LottoNumber(bonusNumber)
            );
        });
    }

    private <T> T retryOnError(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
