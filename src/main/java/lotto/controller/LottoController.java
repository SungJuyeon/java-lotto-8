package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private LottoMachine lottoMachine;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoMachine = new LottoMachine();
    }

    public void start() {
        long lottoCount = userAmountDivisibleByThousand();
        lottoMachine.drawLotto(lottoCount);
        announceAutoLottos();

        Lotto winningLotto = inputWinningNumbers();
        int bonusNumber = inputBonusNumber(winningLotto);

        //당첨 통계 계산하기
        //당첨 통계 출력하기
    }

    public long userAmountDivisibleByThousand() {
        long userAmount = inputView.purchaseAmount();
        return DivisibleAmount.divisibleByThousand(userAmount);
    }

    public void announceAutoLottos() {
        outputView.announceLottoInfo(lottoMachine.getLottos());
    }

    private Lotto inputWinningNumbers() {
        String inputWinningNumber = inputView.winningNumbers();
        ValidateWinningNumber.validateWinningNumber(inputWinningNumber);
        return parseNumbers(inputWinningNumber);
    }

    public Lotto parseNumbers(String inputNumber) {
        String[] parts = inputNumber.trim().split(",");
        List<Integer> inputNumbers = new ArrayList<>();
        for (String part : parts) {
            inputNumbers.add(Integer.parseInt(part));
        }
        return new Lotto(inputNumbers);
    }

    private int inputBonusNumber(Lotto winningLotto) {
        int bonusNumber = inputView.inputBonusNumber();
        ValidateBonusNumber.validateBonusNumber(bonusNumber, winningLotto.getNumbers());
        return bonusNumber;
    }
}
