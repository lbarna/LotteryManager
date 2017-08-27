package com.manager.lottery.LotteryManager.controller;

import com.manager.lottery.LotteryManager.model.WinnerNumbers;
import com.manager.lottery.LotteryManager.service.LotteryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/LotteryManager")
@RestController
public class LotteryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

    @Autowired
    private LotteryService lotteryService;


    @RequestMapping(value = "/numbers", produces = "text/html", method = RequestMethod.GET)
    public ModelAndView listWinnerNumbers() {
        LOGGER.info("Listing winner numbers");

        final List<WinnerNumbers> winnerNumbers = lotteryService.listWinnerNumbers();
        final Map<String, Object> params = new HashMap<>();
        params.put("winnerNumbers", winnerNumbers);

        return new ModelAndView("lottery", params);
    }

    @RequestMapping(value = "/generate", produces = "text/html", method = RequestMethod.GET)
    public String generateWinnerNumbers() {
        final WinnerNumbers generatedWinnerNumbers = lotteryService.generateWinnerNumbers();

        LOGGER.info("Successfully generated a new suggestion: " + generatedWinnerNumbers);

        return generatedWinnerNumbers.toString();
    }

    @RequestMapping(value = "/updateDB", produces = "text/html", method = RequestMethod.GET)
    public String downloadLatestDB() {
        lotteryService.updateDB();
        return "OK";
    }
}
