package lotto.controller;

import lotto.domain.ValidateInput;
import lotto.view.InputView;

public class LottoController {
    private InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void start() {
        long userAmount = userAmountDivisibleByThousand();

    }

    public long userAmountDivisibleByThousand() {
        long userAmount = inputView.purchaseAmount();
        if(ValidateInput.isDivisibleByThousand(userAmount))
            return userAmount / 1_000;
    }
}
