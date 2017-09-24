package com.manager.lottery.LotteryManager.service;

import com.manager.lottery.LotteryManager.model.WinnerNumbers;
import com.manager.lottery.LotteryManager.repository.LotteryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StatisticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsService.class);

    @Autowired
    private LotteryRepository repository;

    public Map<String, Integer> getMostFrequentNumbers(final int top) {
        final List<WinnerNumbers> winnerNumbers = repository.findAll();

        final IntStream flatMap = winnerNumbers.stream().flatMapToInt(element -> Arrays.stream(element.getNumbers()));

        final Map<Integer, Integer> allFrequencies = flatMap.boxed().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));

        final Map<String, Integer> mostFrequents = allFrequencies.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue)).skip(allFrequencies.keySet().size() - top)
                .collect(Collectors.toMap(entry -> entry.getKey().toString(), Map.Entry::getValue));

        LOGGER.info("mostFrequents: " + mostFrequents);

        return mostFrequents;
    }

    public long getHighestPrize() {
        final List<WinnerNumbers> winnerNumbers = repository.findAll();

        return winnerNumbers.stream().filter(value -> !value.getPrize().equals("0 Ft")).mapToLong(value -> parseLong(value.getPrize())).max().orElse(0);
    }

    private long parseLong(final String input) {
        return Long.parseLong(input.substring(0, input.length() - 3).replaceAll("\\s", ""));
    }

    public int getHighestShareCount() {
        final List<WinnerNumbers> winnerNumbers = repository.findAll();

        return winnerNumbers.stream().mapToInt(WinnerNumbers::getWinnersCount).max().orElse(0);
    }

    public int getTotalWinnersCount() {
        final List<WinnerNumbers> winnerNumbers = repository.findAll();

        return winnerNumbers.stream().mapToInt(WinnerNumbers::getWinnersCount).sum();
    }

    public double getWinPercentage() {
        final List<WinnerNumbers> winnerNumbers = repository.findAll();

        final long totalGames = winnerNumbers.stream().filter(game -> game.getYear() >= 1998).count();
        final long gamesWithWinner = winnerNumbers.stream().filter(game -> game.getWinnersCount() != 0).count();

        return ((double) gamesWithWinner) / totalGames * 100;
    }
}
