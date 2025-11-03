package lotto.ui.dto;

import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;

public record LottoNumbersDto(List<Integer> numbers) {

    public static LottoNumbersDto from(Lotto lotto) {
        List<Integer> lottoNumbers = lotto.numbers()
                .stream()
                .map(LottoNumber::number)
                .sorted()
                .toList();
        return new LottoNumbersDto(lottoNumbers);
    }
}
