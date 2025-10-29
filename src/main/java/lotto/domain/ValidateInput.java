package lotto.domain;

public class ValidateInput {
    public static long divisibleByThousand(long userAmount) {
        if(userAmount % 1000 != 0)
            throw new IllegalArgumentException("[Error] UserAmount must be divisible by 1000");
        return userAmount / 1_000;
    }
}
