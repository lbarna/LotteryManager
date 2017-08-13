package com.manager.lottery.LotteryManager.service;

import com.manager.lottery.LotteryManager.model.WinnerNumbers;
import com.manager.lottery.LotteryManager.repository.LotteryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LotteryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryService.class);

    @Autowired
    private LotteryRepository repository;

    public List<WinnerNumbers> listWinnerNumbers() {
        final List<WinnerNumbers> winnerNumbers = repository.findAll();

        winnerNumbers.forEach(number -> LOGGER.info("The winner numbers are: " + Arrays.toString(number.getNumbers())));

        return winnerNumbers;
    }

    public WinnerNumbers generateWinnerNumbers() {
        final WinnerNumbers winnerNumbers = createNewWinnerNumbers();
        LOGGER.info("Generated winner numbers: " + winnerNumbers);

        return repository.save(winnerNumbers);
    }

    private WinnerNumbers createNewWinnerNumbers() {
        final WinnerNumbers winnerNumbers = new WinnerNumbers();
        winnerNumbers.setNumbers(generateRandom());

        return winnerNumbers;
    }

    private int[] generateRandom() {
        final int[] numbers = new int[5];

        for (int i = 0; i < 5; i++) {
            numbers[i] = (int) (Math.random() * 90);
        }

        Arrays.sort(numbers);

        return numbers;
    }

}
