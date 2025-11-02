package lotto;

import lotto.domain.LottoRank;
import lotto.domain.WinningStatistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinningStatisticsTest {

    @DisplayName("당첨 통계가 올바르게 집계된다.")
    @Test
    void 당첨_통계가_올바르게_집계된다() {
        WinningStatistics statistics = new WinningStatistics(8000);

        statistics.addResult(LottoRank.FIFTH);
        statistics.addResult(LottoRank.FIFTH);
        statistics.addResult(LottoRank.FOURTH);

        assertThat(statistics.getCount(LottoRank.FIFTH)).isEqualTo(2);
        assertThat(statistics.getCount(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(statistics.getCount(LottoRank.THIRD)).isEqualTo(0);
    }

    @DisplayName("수익률이 올바르게 계산된다.")
    @Test
    void 수익률이_올바르게_계산된다() {
        WinningStatistics statistics = new WinningStatistics(8000);

        statistics.addResult(LottoRank.FIFTH); // 5,000원

        double profitRate = statistics.calculateProfitRate();
        assertThat(profitRate).isEqualTo(62.5);
    }

    @DisplayName("당첨이 없으면 수익률은 0이다.")
    @Test
    void 당첨이_없으면_수익률은_0이다() {
        WinningStatistics statistics = new WinningStatistics(8000);

        double profitRate = statistics.calculateProfitRate();
        assertThat(profitRate).isEqualTo(0.0);
    }
}