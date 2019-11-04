package lotto;

import java.util.List;

public class Lotto {
    private List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return lottoNumbers.equals(lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        final int prime = 59;
        int hashCode = 1;
        for (LottoNumber lottoNumber : lottoNumbers) {
            hashCode = prime * hashCode + (lottoNumber == null ? 0 : lottoNumber.hashCode());
        }
        return hashCode;
    }
}
