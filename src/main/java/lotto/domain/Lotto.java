package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MIN_LOTTO_NUMBER_RANGE = 1;
    private static final int MAX_LOTTO_NUMBER_RANGE = 45;

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
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(
                        "로또 번호는 %s부터 %s사이의 숫자여야 합니다.".formatted(MIN_LOTTO_NUMBER_RANGE, MAX_LOTTO_NUMBER_RANGE)
                );
            }
        }
    }

    public int countMatching(Lotto other) {
        return (int) other.numbers.stream()
                .filter(this::match)
                .count();
    }

    public boolean match(int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
