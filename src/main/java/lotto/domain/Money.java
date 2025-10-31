package lotto.domain;

public class Money {

    public Money(int money) {
        validateMoney(money);
    }

    private static void validateMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
        }
    }
}
