package lotto.domain;

public class DivisibleAmount {
    public static long divisibleByThousand(long userAmount) {
        if(userAmount % 1000 != 0)
            throw new IllegalArgumentException("[ERROR] 구입금액은 1000원 단위여야 합니다.");
        return userAmount / 1_000;
    }
}
