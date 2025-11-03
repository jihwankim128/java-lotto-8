package lotto.ui;

import java.util.List;
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
        try {
            int money = inputView.readPurchaseAmount();
            return new Money(money);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readMoney();
        }
    }

    @Override
    public WinningNumbers readWinningNumbers() {
        try {
            Lotto winningLotto = readWinningLotto();
            LottoNumber bonusNumber = readBonusNumber();
            return new WinningNumbers(winningLotto, bonusNumber);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readWinningNumbers();
        }
    }

    private LottoNumber readBonusNumber() {
        int bonusNumber = inputView.readBonusNumber();
        return new LottoNumber(bonusNumber);
    }

    private Lotto readWinningLotto() {
        List<Integer> winningNumbers = inputView.readWinningNumbers();
        return Lotto.generateOf(winningNumbers);
    }
}
