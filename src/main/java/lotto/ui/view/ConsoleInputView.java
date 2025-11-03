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
        return parseNumbers(Console.readLine());
    }

    public int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        return parseNumber(Console.readLine());
    }

    private List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(this::parseNumber)
                .toList();
    }

    private int parseNumber(String input) {
        try {
            return Integer.parseInt(input.strip());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수 형식이 아닙니다.");
        }
    }
}