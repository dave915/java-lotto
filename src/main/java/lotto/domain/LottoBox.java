package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoBox {
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;
    private static final int LOTTO_LENGTH_MIN = 1;
    private static final int LOTTO_LENGTH_MAX = 6;
    private static final String NUMBER_STRING_SEPARATOR = ", |,";
    private static final String OUT_OF_RANGE_ERROR_MESSAGE = "로또 숫자는 6개만 입력 가능합니다.";
    private static final String CONVERT_NUMBER_ERROR_MESSAGE = "숫자 이외의 값이 입력 되었습니다.";

    private static List<LottoNumber> defaultNumbers = new ArrayList<>();

    static {
        for (int i = LOTTO_START_NUMBER; i <= LOTTO_END_NUMBER; i++) {
            defaultNumbers.add(new LottoNumber(i));
        }
    }

    public static List<LottoNumber> generateNumbers() {
        Collections.shuffle(defaultNumbers, new Random(System.currentTimeMillis()));
        return new ArrayList<>(defaultNumbers.subList(0, 6));
    }

    public static List<LottoNumber> parseNumberString(String numberString) {
        String[] numberStringArray = numberString.split(NUMBER_STRING_SEPARATOR);
        checkLottoLength(numberStringArray);
        return convertLottoNumbers(numberStringArray);
    }

    private static void checkLottoLength(String[] numberStringArray) {
        if (isOutOfRange(numberStringArray)) {
            throw new IllegalArgumentException(OUT_OF_RANGE_ERROR_MESSAGE);
        }
    }

    private static boolean isOutOfRange(String[] numberStringArray) {
        return numberStringArray.length > LOTTO_LENGTH_MAX || numberStringArray.length < LOTTO_LENGTH_MIN;
    }

    private static List<LottoNumber> convertLottoNumbers(String[] numberStringArray) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (String numberString : numberStringArray) {
            lottoNumbers.add(new LottoNumber(convertNumber(numberString)));
        }

        return lottoNumbers;
    }

    private static int convertNumber(String numberString) {
        try {
            return Integer.parseInt(numberString);
        } catch (NumberFormatException e) {
            throw new  NumberFormatException(CONVERT_NUMBER_ERROR_MESSAGE);
        }
    }
}