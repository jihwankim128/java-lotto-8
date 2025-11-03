package lotto.application;

import lotto.domain.vo.Lottos;
import lotto.domain.vo.WinningResult;

public interface OutputPresenter {

    void presentPurchase(Lottos lottos);

    void presentResult(WinningResult result, double profitRatio);
}
