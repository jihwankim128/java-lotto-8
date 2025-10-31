package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 %s개여야 합니다.".formatted(LOTTO_NUMBER_COUNT));
        }
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
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
