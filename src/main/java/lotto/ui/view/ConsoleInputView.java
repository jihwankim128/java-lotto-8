package lotto.ui.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class ConsoleInputView {

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return parseNumber(Console.readLine());
    }

    public List<Integer> readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return readNumbers();
    }

    public int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        return parseNumber(Console.readLine());
    }

    private List<Integer> readNumbers() {
        return Arrays.stream(Console.readLine().split(","))
                .map(this::parseNumber)
                .toList();
    }

    private int parseNumber(String textNumber) {
        try {
            return Integer.parseInt(textNumber.strip());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수 형식이 아닙니다.");
        }
    }
}