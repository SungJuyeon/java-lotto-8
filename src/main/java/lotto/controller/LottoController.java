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
        long userAmount = inputView.purchaseAmount();
        long lottoCount = DivisibleAmount.divisibleByThousand(userAmount);

        lottoMachine.drawLotto(lottoCount);
        announceAutoLottos();

        Lotto winningLotto = inputWinningNumbers();
        int bonusNumber = inputBonusNumber(winningLotto);

        WinningStatistics statistics = calculateWinningStatistics(winningLotto, bonusNumber, userAmount);
        outputView.printWinningStatistics(statistics);
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

    private WinningStatistics calculateWinningStatistics(Lotto winningLotto, int bonusNumber, long purchaseAmount) {
        WinningStatistics statistics = new WinningStatistics(purchaseAmount);
        List<Lotto> userLottos = lottoMachine.getLottos();

        for (Lotto userLotto : userLottos) {
            LottoResult result = new LottoResult(userLotto, winningLotto, bonusNumber);
            LottoRank rank = result.calculateRank();
            statistics.addResult(rank);
        }

        return statistics;
    }
}
