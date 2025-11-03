package lotto.controller;

import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;

public interface InputHandler {

    Money readMoney();

    WinningNumbers readWinningNumbers();
}
