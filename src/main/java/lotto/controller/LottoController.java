package lotto.controller;

import lotto.domain.DivisibleAmount;
import lotto.view.InputView;

public class LottoController {
    private InputView inputView;

    public LottoController() {
        this.inputView = new InputView();
    }

    public void start() {
        long userAmount = userAmountDivisibleByThousand();

    }

    public long userAmountDivisibleByThousand() {
        long userAmount = inputView.purchaseAmount();
        return DivisibleAmount.divisibleByThousand(userAmount);
    }
}
