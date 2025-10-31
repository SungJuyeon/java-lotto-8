package lotto.controller;

import lotto.domain.DivisibleAmount;
import lotto.domain.LottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

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

        //당첨번호 입력받기

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

}
