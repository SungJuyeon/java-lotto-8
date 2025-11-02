package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {
    private final Map<LottoRank, Integer> statistics;
    private final long totalPurchaseAmount;

    public WinningStatistics(long totalPurchaseAmount) {
        this.statistics = new EnumMap<>(LottoRank.class);
        this.totalPurchaseAmount = totalPurchaseAmount;
        initializeStatistics();
    }

    private void initializeStatistics() {
        for (LottoRank rank : LottoRank.values()) {
            if (rank.isWinning()) {
                statistics.put(rank, 0);
            }
        }
    }

    public void addResult(LottoRank rank) {
        if (rank.isWinning()) {
            statistics.put(rank, statistics.get(rank) + 1);
        }
    }

    public int getCount(LottoRank rank) {
        return statistics.getOrDefault(rank, 0);
    }

    public double calculateProfitRate() {
        long totalPrize = calculateTotalPrize();
        return (double) totalPrize / totalPurchaseAmount * 100;
    }

    private long calculateTotalPrize() {
        return statistics.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    public List<LottoRank> getWinningRanks() {
        return List.of(LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD, LottoRank.SECOND, LottoRank.FIRST);
    }
}