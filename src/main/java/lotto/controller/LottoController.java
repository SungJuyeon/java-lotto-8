package lotto.controller;

import lotto.domain.ValidateInput;
import lotto.view.InputView;

public class LottoController {
    private InputView inputView;

    public LottoController() {
    }

    public void start() {
        long userAmount = userAmountDivisibleByThousand();

    }

    public long userAmountDivisibleByThousand() {
        try {
            long userAmount = inputView.purchaseAmount();
            return ValidateInput.divisibleByThousand(userAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[Error] UserAmount is too big");
        }
    }
}
