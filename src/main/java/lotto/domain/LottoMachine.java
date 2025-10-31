package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private final List<Lotto> lottos = new ArrayList<>();

    public void drawLotto(long lottoCount) {
        for(int i = 0; i < lottoCount; i++) {
            Lotto lotto = new Lotto(randomLottoNumber());
            lottos.add(lotto);
        }
    }

    public List<Integer> randomLottoNumber() {
        //1에서 45 사이의 중복되지 않은 정수 6개 반환
        return Randoms.pickUniqueNumbersInRange(1,45,6);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}
