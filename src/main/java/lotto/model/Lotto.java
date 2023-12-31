package lotto.model;

import lotto.util.LottoConstants;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        numberOrder(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private void numberOrder(List<Integer> numbers) {
        Collections.sort(numbers);
    }

    public String toString() {
        String lottoNumbers = "";

        for (int i = 0; i < numbers.size() - 1; i++) {
            lottoNumbers += numbers.get(i) + ", ";
        }
        lottoNumbers += numbers.get(numbers.size() - 1);

        return lottoNumbers;
    }

    public String lottoResult(List<Integer> winningNumber, Integer bonusNumber) {
        String correctCount = compareNumbers(winningNumber);

        if (correctCount.equals(LottoConstants.CORRECT_FIVE) && numbers.contains(bonusNumber)) {
            return LottoConstants.CORRECT_FIVE_WITH_BONUS;
        }

        return correctCount;
    }

    private String compareNumbers(List<Integer> winningNumber) {
        int containCount = 0;
        int minWin = Integer.parseInt(LottoConstants.CORRECT_TREE);

        for (int number : numbers) {
            if (winningNumber.contains(number)) {
                containCount++;
            }
        }

        if (containCount < minWin) {
            return "-1";
        }
        return String.valueOf(containCount);
    }
}
