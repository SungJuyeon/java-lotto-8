package lotto.controller;

import lotto.domain.DivisibleAmount;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.ValidateWinningNumber;
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

        inputWinningNumbers();

        //보너스 번호 입력받기

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

    public void inputWinningNumbers() {
        String inputWinningNumber = inputView.winningNumbers();
        ValidateWinningNumber.validateWinningNumber(inputWinningNumber);
        Lotto winningLotto = parseNumbers(inputWinningNumber);
    }

    public Lotto parseNumbers(String inputNumber) {
        String[] parts = inputNumber.trim().split(",");
        List<Integer> inputNumbers = new ArrayList<>();
        for (String part : parts) {
            inputNumbers.add(Integer.parseInt(part));
        }
        return new Lotto(inputNumbers);
    }
}
