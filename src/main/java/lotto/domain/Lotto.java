package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public int countWinning(Lotto lotto) {
        int count = 0;
        for (Integer number : lotto.numbers) {
            if (isWinning(number)) {
                count++;
            }
        }
        return count;
    }

    public boolean isWinning(int number) {
        return numbers.contains(number);
    }
}
