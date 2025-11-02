package lotto.domain.vo;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_NUMBER_COUNT = 6;
    
    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto generateOf(List<Integer> numbers) {
        validate(numbers);
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.collectingAndThen(Collectors.toUnmodifiableList(), Lotto::new));
    }

    private static void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 %s개여야 합니다.".formatted(LOTTO_NUMBER_COUNT));
        }
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }

    public int countMatching(Lotto other) {
        return (int) other.numbers.stream()
                .filter(this::match)
                .count();
    }

    public boolean match(LottoNumber number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::number)
                .toList();
    }
}
