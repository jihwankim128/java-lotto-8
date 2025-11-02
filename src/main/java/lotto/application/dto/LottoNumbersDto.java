package lotto.application.dto;

import java.util.List;
import lotto.domain.vo.Lotto;

public record LottoNumbersDto(List<Integer> numbers) {

    public static LottoNumbersDto from(Lotto lotto) {
        List<Integer> lottoNumbers = lotto.getNumbers()
                .stream()
                .sorted()
                .toList();
        return new LottoNumbersDto(lottoNumbers);
    }
}
