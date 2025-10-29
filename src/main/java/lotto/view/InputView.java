package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static final String InputUserPurchaseAmount = "구입금액을 입력해 주세요.";

    public long purchaseAmount() {
        System.out.println(InputUserPurchaseAmount);
        try {
            return Long.parseLong(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.");
        }
    }
}
