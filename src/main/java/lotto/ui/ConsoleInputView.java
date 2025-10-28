package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class ConsoleInputView {

    public int readPurchaseAmount() {
        return Integer.parseInt(Console.readLine());
    }

    public List<Integer> readWinningNumbers() {
        String winningNumbers = Console.readLine();
        return Arrays.stream(winningNumbers.split(","))
                .map(Integer::parseInt)
                .toList();
    }

    public int readBonusNumber() {
        return Integer.parseInt(Console.readLine());
    }
}
