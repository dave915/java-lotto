package lotto.domain;

import java.util.Arrays;
import java.util.List;

public class Lotto {

	private List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public int match(Lotto lotto) {
		int match = 0;
		for(Integer number : numbers) {
			if(lotto.hasNumber(number)) {
				++match;
			}
		}
		return match;
	}

	public boolean hasNumber(Integer number) {
		return numbers.contains(number);
	}

	@Override
	public String toString() {
		return Arrays.toString(numbers.toArray());
	}
}