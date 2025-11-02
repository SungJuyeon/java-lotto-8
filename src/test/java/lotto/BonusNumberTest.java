package lotto;

import lotto.domain.ValidateBonusNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

class BonusNumberTest {

    @DisplayName("보너스 번호가 1-45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 보너스_번호가_범위를_벗어나면_예외가_발생한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> ValidateBonusNumber.validateBonusNumber(0, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> ValidateBonusNumber.validateBonusNumber(46, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> ValidateBonusNumber.validateBonusNumber(1, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> ValidateBonusNumber.validateBonusNumber(6, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 보너스 번호는 예외가 발생하지 않는다.")
    @Test
    void 유효한_보너스_번호는_예외가_발생하지_않는다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        assertThatCode(() -> ValidateBonusNumber.validateBonusNumber(7, winningNumbers))
                .doesNotThrowAnyException();
    }
}