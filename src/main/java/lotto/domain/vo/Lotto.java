package lotto.domain.vo;

import java.util.HashSet;
import java.util.List;

public record Lotto(List<LottoNumber> numbers) {

    public static final int LOTTO_NUMBER_COUNT = 6;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public static Lotto generateOf(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        return new Lotto(lottoNumbers);
    }

    private static void validate(List<LottoNumber> numbers) {
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
}
