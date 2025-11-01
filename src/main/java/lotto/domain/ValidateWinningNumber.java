package lotto.domain;

import java.util.List;

public class ValidateWinningNumber {
    private static final String WINNING_NUMBER_SPLITTER = ",";
    public static void validateWinningNumber(String winningNumbers) {
        List<String> numbers = List.of(winningNumbers.split(WINNING_NUMBER_SPLITTER));
        if(numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        if(numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        }
        for(String InputNumber : numbers) {
            try{
                int number = Integer.parseInt(InputNumber.trim());
                if(number < 1 || number > 45){
                    throw new IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.");
                }
            } catch(NumberFormatException e){
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
            }
        }
    }
}
