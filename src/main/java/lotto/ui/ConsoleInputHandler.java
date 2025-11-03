package lotto.ui;

import java.util.List;
import java.util.function.Supplier;
import lotto.controller.handler.InputHandler;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;

public class ConsoleInputHandler implements InputHandler {

    private final ConsoleInputView inputView = new ConsoleInputView();
    private final ConsoleOutputView outputView = new ConsoleOutputView();

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
