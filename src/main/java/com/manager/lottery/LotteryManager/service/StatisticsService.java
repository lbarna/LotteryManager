package com.manager.lottery.LotteryManager.service;

import com.manager.lottery.LotteryManager.model.WinnerNumbers;
import com.manager.lottery.LotteryManager.repository.LotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private LotteryRepository repository;

    public int[] getMostFrequentNumbers(final int top) {
        final int[] topNumbers = new int[top];
        final List<WinnerNumbers> winnerNumbers = repository.findAll();

        //TODO finish it

        return topNumbers;
    }

}
