package lotto.domain;

import java.util.List;

public class LottoResult {
    private final Lotto userLotto;
    private final Lotto winningLotto;
    private final int bonusNumber;

    public LottoResult(Lotto userLotto, Lotto winningLotto, int bonusNumber) {
        this.userLotto = userLotto;
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank calculateRank() {
        int matchCount = countMatchingNumbers();
        boolean matchBonus = isMatchBonus();

        return LottoRank.valueOf(matchCount, matchBonus);
    }

    private int countMatchingNumbers() {
        List<Integer> userNumbers = userLotto.getNumbers();
        List<Integer> winningNumbers = winningLotto.getNumbers();

        return (int) userNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean isMatchBonus() {
        return userLotto.getNumbers().contains(bonusNumber);
    }
}
