package lotto.ui.dto;

import java.util.List;
import lotto.domain.vo.Lottos;

public record PurchaseDto(int quantity, List<LottoNumbersDto> lottos) {

    public static PurchaseDto from(Lottos purchaseLotto) {
        List<LottoNumbersDto> lottos = purchaseLotto.lottos()
                .stream()
                .map(LottoNumbersDto::from)
                .toList();
        return new PurchaseDto(lottos.size(), lottos);
    }
}
