package com.manager.lottery.LotteryManager.service;

import com.manager.lottery.LotteryManager.model.WinnerNumbers;
import com.manager.lottery.LotteryManager.repository.LotteryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class LotteryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryService.class);

    @Autowired
    private LotteryRepository repository;

    @Autowired
    private CSVDownloaderService csvDownloaderService;

    public List<WinnerNumbers> listWinnerNumbers() {
        return repository.findAll();
    }

    public WinnerNumbers generateWinnerNumbers() {
        final WinnerNumbers winnerNumbers = new WinnerNumbers();
        winnerNumbers.setNumbers(generateRandom());

        LOGGER.info("Generated winner numbers: " + winnerNumbers);

        return winnerNumbers;
    }

    public void updateDB() {
        final List<WinnerNumbers> latestWinnerNumbers = csvDownloaderService.downloadLatest();

        repository.deleteAll();
        LOGGER.info("Successfully truncated data in DB.");

        repository.insert(latestWinnerNumbers);
        LOGGER.info("Successfully updated database with latest data.");
    }

    private int[] generateRandom() {
        return new Random().ints(5, 1, 91).sorted().toArray();
    }

}
