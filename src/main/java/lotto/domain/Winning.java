package lotto.domain;

public enum Winning {

    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FOURTH(50_000, 4),
    FIFTH(5_000, 3),
    GROUND(0, 0);

    private final int amount;
    private final int correctCount;

    Winning(int amount, int correctCount) {
        this.amount = amount;
        this.correctCount = correctCount;
    }

    public static Winning of(int correctCount, boolean bonus) {
        if (correctCount == 5) {
            if (bonus) {
                return SECOND;
            }
            return THIRD;
        }
        for (Winning winning : Winning.values()) {
            if (winning.correctCount == correctCount) {
                return winning;
            }
        }
        return GROUND;
    }

    public int getAmount() {
        return amount;
    }

    public int getCorrectCount() {
        return correctCount;
    }
}
