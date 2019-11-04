package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoGameTest {
    @Test
    void moneyValidate() {
        int money = 900;
        assertThatThrownBy(() -> {
            LottoGame lottoGame = new LottoGame(money);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("금액을 넣었을때 금액 / 1000 만큼 로또가 생성되는지 확인한다.")
    void lottoSize() {
        int money = 3000;
        int expectedSize = money / 1000;

        LottoGame lottoGame = new LottoGame(money);

        assertThat(lottoGame.lottoSize()).isEqualTo(expectedSize);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 6", "1,2,3,4,5,6"})
    @DisplayName("당첨 번호를 셋팅한다.")
    void winningLotto(String winningLottoString) {
        int money = 1000;
        LottoGame lottoGame = new LottoGame(money);

        lottoGame.winningLotto(winningLottoString);

        assertThat(lottoGame.isInitWinningLotto()).isTrue();
    }

    @Test
    @DisplayName("LottoRank당 하나씩 결과가 나오는지 확인한다.")
    void result() {
        List<Lotto> testLottos = getTestLottos();
        String testWinningNumbers = "1, 2, 3, 4, 5, 6";

        LottoGame lottoGame = new LottoGame(testLottos);
        lottoGame.winningLotto(testWinningNumbers);

        LottoResult lottoResult = lottoGame.result();
        for (LottoRank lottoRank : LottoRank.values()) {
            assertThat(lottoResult.rankCount(lottoRank)).isEqualTo(1);
        }
    }

    private List<Lotto> getTestLottos() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(getTestLotto(new int[]{1, 2, 3, 4, 5, 6}));
        lottos.add(getTestLotto(new int[]{2, 3, 4, 5, 6, 7}));
        lottos.add(getTestLotto(new int[]{3, 4, 5, 6, 7, 8}));
        lottos.add(getTestLotto(new int[]{4, 5, 6, 7, 8, 9}));

        return lottos;
    }

    private Lotto getTestLotto(int[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

        return new Lotto(lottoNumbers);
    }
}
