package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @DisplayName("6개 모두 일치하면 1등이다.")
    @Test
    void 여섯개_모두_일치하면_1등이다() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoResult result = new LottoResult(userLotto, winningLotto, 7);

        assertThat(result.calculateRank()).isEqualTo(LottoRank.FIRST);
    }

    @DisplayName("5개 일치하고 보너스 번호가 일치하면 2등이다.")
    @Test
    void 다섯개_일치하고_보너스_번호가_일치하면_2등이다() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoResult result = new LottoResult(userLotto, winningLotto, 7);

        assertThat(result.calculateRank()).isEqualTo(LottoRank.SECOND);
    }

    @DisplayName("5개 일치하고 보너스 번호가 일치하지 않으면 3등이다.")
    @Test
    void 다섯개_일치하고_보너스_번호가_일치하지_않으면_3등이다() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoResult result = new LottoResult(userLotto, winningLotto, 7);

        assertThat(result.calculateRank()).isEqualTo(LottoRank.THIRD);
    }

    @DisplayName("3개 일치하면 5등이다.")
    @Test
    void 세개_일치하면_5등이다() {
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoResult result = new LottoResult(userLotto, winningLotto, 7);

        assertThat(result.calculateRank()).isEqualTo(LottoRank.FIFTH);
    }

    @DisplayName("2개 이하 일치하면 낙첨이다.")
    @Test
    void 두개_이하_일치하면_낙첨이다() {
        Lotto userLotto = new Lotto(List.of(1, 2, 10, 11, 12, 13));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoResult result = new LottoResult(userLotto, winningLotto, 7);

        assertThat(result.calculateRank()).isEqualTo(LottoRank.NONE);
    }
}