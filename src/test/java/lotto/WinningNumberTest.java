package lotto;

import lotto.domain.ValidateWinningNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

class WinningNumberTest {

    @DisplayName("당첨 번호가 6개가 아니면 예외가 발생한다.")
    @Test
    void 당첨_번호가_6개가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> ValidateWinningNumber.validateWinningNumber("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> ValidateWinningNumber.validateWinningNumber("1,2,3,4,5,6,7"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 중복이 있으면 예외가 발생한다.")
    @Test
    void 당첨_번호에_중복이_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> ValidateWinningNumber.validateWinningNumber("1,2,3,4,5,5"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 1-45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 당첨_번호가_범위를_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> ValidateWinningNumber.validateWinningNumber("0,2,3,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> ValidateWinningNumber.validateWinningNumber("1,2,3,4,5,46"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 숫자가 아닌 값이 있으면 예외가 발생한다.")
    @Test
    void 당첨_번호에_숫자가_아닌_값이_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> ValidateWinningNumber.validateWinningNumber("1,2,3,a,5,6"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 당첨 번호는 예외가 발생하지 않는다.")
    @Test
    void 유효한_당첨_번호는_예외가_발생하지_않는다() {
        assertThatCode(() -> ValidateWinningNumber.validateWinningNumber("1,2,3,4,5,6"))
                .doesNotThrowAnyException();
    }
}