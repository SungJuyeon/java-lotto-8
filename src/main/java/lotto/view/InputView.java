package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static final String InputUserPurchaseAmount = "구입금액을 입력해 주세요.";

    public long purchaseAmount() {
        System.out.println(InputUserPurchaseAmount);
        return Long.parseLong(Console.readLine());
    }
}
