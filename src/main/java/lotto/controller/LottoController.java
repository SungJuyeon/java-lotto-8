package lotto.controller;

import lotto.domain.ValidateInput;
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
        return ValidateInput.divisibleByThousand(userAmount);
    }
}
