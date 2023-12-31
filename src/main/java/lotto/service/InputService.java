package lotto.service;

import lotto.util.LottoConstants;
import lotto.validation.InputValidation;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class InputService {
    InputView inputView;
    InputValidation inputValidation;
    OutputView outputView;

    public InputService(InputView inputView, InputValidation inputValidation, OutputView outputView) {
        this.inputView = inputView;
        this.inputValidation = inputValidation;
        this.outputView = outputView;
    }

    public int readPurchasingAmount() {
        while(true) {
            String purchasingAmount = inputView.readInput();

            try {
                inputValidation.validatePurchasingAmount(purchasingAmount);
                return getPurchaseCount(purchasingAmount);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Integer> readWinningNumbers() {
        while(true) {
            try {
                String winningNumbers = inputView.readInput();
                inputValidation.validateWinningNumber(winningNumbers);
                return getWinningNumber(winningNumbers);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int readBonusNumber(List<Integer> winningNumbers) {
        while(true) {
            try {
                String bonusNumber = inputView.readInput();

                inputValidation.validateBonusNumber(winningNumbers, bonusNumber);
                return Integer.parseInt(bonusNumber);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Integer getPurchaseCount(String purchasingAmount) {
        return Integer.parseInt(purchasingAmount) / LottoConstants.DIVISION_THOUSAND;
    }

    private List<Integer> getWinningNumber(String winningNumbers) {
        List<Integer> winningNumber = new ArrayList<>();
        String[] numbers = winningNumbers.split(LottoConstants.DIVISION_STANDARD);

        for (String number : numbers) {
            int num = Integer.parseInt(number);
            winningNumber.add(num);
        }
        return winningNumber;
    }
}
