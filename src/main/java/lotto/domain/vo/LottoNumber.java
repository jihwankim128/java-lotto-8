package lotto.domain.vo;

public record LottoNumber(int number) {

    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 45;

    public LottoNumber {
        validateLottoNumber(number);
    }

    private void validateLottoNumber(int number) {
        if (number < MIN_RANGE || number > MAX_RANGE) {
            throw new IllegalArgumentException(
                    "로또 번호는 %s부터 %s사이의 숫자여야 합니다.".formatted(MIN_RANGE, MAX_RANGE)
            );
        }
    }
}
