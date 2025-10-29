package lotto.domain;

public class ValidateInput {
    public static boolean isDivisibleByThousand(long userAmount) {
        if(userAmount % 1000 != 0)
            throw new IllegalArgumentException("[Error] UserAmount must be divisible by 1000");
        return true;
    }
}
