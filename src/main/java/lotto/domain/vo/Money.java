package lotto.domain.vo;

public record Money(int money) {

    private static final int UNIT = 1000;

    public Money {
        validateMoney(money);
    }

    private static void validateMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
        }
        if (money % UNIT != 0) {
            throw new IllegalArgumentException("금액은 %s원 단위여야 합니다.".formatted(UNIT));
        }
    }

    public int calculateQuantity(int unitPrice) {
        return money / unitPrice;
    }
}
