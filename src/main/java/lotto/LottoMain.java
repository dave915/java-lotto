package lotto;

import lotto.domain.LottoGame;
import lotto.view.InputView;
import lotto.view.OutPutView;

public class LottoMain {
    public static void main(String[] args) {
        int money = InputView.inputMoney();

        LottoGame lottoGame = new LottoGame(money);
        OutPutView.printGameCount(lottoGame.lottoSize());
    }
}
