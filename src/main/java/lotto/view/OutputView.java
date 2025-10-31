package lotto.view;

import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutputView {
    private static final String announceLottoCount = "%d개를 구매했습니다.";
    public void announceLottoInfo(List<Lotto> lottos) {
        System.out.println(String.format(announceLottoCount, lottos.size()));
        for (Lotto lotto : lottos) {
            printLottos(lotto);
        }
        System.out.println();
    }

    public void printLottos(Lotto lotto) {
        List<Integer> lottoNumbers = new ArrayList<>(lotto.getNumbers());
        Collections.sort(lottoNumbers);
        System.out.println(lottoNumbers);
    }
}
