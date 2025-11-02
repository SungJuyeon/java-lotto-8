package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningStatistics;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutputView {
    private static final String announceLottoCount = "%d개를 구매했습니다.";
    private static final DecimalFormat moneyFormat = new DecimalFormat("#,###");

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

    public void printWinningStatistics(WinningStatistics statistics) {
        System.out.println("당첨 통계");
        System.out.println("---");

        for (LottoRank rank : statistics.getWinningRanks()) {
            printRankStatistics(rank, statistics.getCount(rank));
        }

        printProfitRate(statistics.calculateProfitRate());
    }

    private void printRankStatistics(LottoRank rank, int count) {
        String formattedMoney = moneyFormat.format(rank.getPrizeMoney());
        System.out.printf("%s (%s원) - %d개\n", rank.getDescription(), formattedMoney, count);
    }

    private void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.\n", profitRate);
    }
}
