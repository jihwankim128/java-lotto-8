package lotto.domain.vo;

public record Money(int money) {

    private static final int UNIT = 1000;

    public Money {
        validateMoney(money);
    }

    private static void validateMoney(int money) {
        if (money < UNIT) {
            throw new IllegalArgumentException("최소 금액은 %s원 입니다.".formatted(UNIT));
        }
        if (money % UNIT != 0) {
            throw new IllegalArgumentException("금액은 %s원 단위여야 합니다.".formatted(UNIT));
        }
    }

    public int calculateQuantity(int unitPrice) {
        if (unitPrice <= 0) {
            throw new IllegalArgumentException("단위 가격은 양수여야 합니다.");
        }
        return money / unitPrice;
    }

    public double calculatePercentageOf(double part) {
        return part / money * 100;
    }
}
