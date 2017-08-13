package com.manager.lottery.LotteryManager.controller;

import com.manager.lottery.LotteryManager.model.WinnerNumbers;
import com.manager.lottery.LotteryManager.service.LotteryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/LotteryManager")
@RestController
public class LotteryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

    @Autowired
    private LotteryService lotteryService;


    @RequestMapping(value = "/numbers", produces = "text/html", method = RequestMethod.GET)
    public String listWinnerNumbers() {

        final List<WinnerNumbers> winnerNumbers = lotteryService.listWinnerNumbers();

        return winnerNumbers.stream().map(WinnerNumbers::toString).collect(Collectors.joining(" | "));
    }

    @RequestMapping(value = "/generate", produces = "text/html", method = RequestMethod.GET)
    public String generateWinnerNumbers() {
        WinnerNumbers generatedWinnerNumbers = lotteryService.generateWinnerNumbers();

        return generatedWinnerNumbers.toString();
    }
}
