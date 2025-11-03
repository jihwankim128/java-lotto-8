package lotto.domain.vo;

public record LottoNumber(int number) {

    public static final int MIN_RANGE = 1;
    public static final int MAX_RANGE = 45;
    
    private static final String INVALID_RANGE_FORMAT = "로또 번호는 %s부터 %s사이의 숫자여야 합니다.";

    public LottoNumber {
        validateLottoNumber(number);
    }

    private void validateLottoNumber(int number) {
        if (number < MIN_RANGE || number > MAX_RANGE) {
            throw new IllegalArgumentException(INVALID_RANGE_FORMAT.formatted(MIN_RANGE, MAX_RANGE));
        }
    }
}
