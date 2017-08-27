package com.manager.lottery.LotteryManager.service;

import com.manager.lottery.LotteryManager.model.WinnerNumbers;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVReader {

    private static final String LOTTERY_DATA_SOURCE_PATH = "otos.csv";

    public List<WinnerNumbers> getPreviousWinnerNumbers() {
        final List<WinnerNumbers> previousWinnerNumbers = new ArrayList<>();
        final File file = getFile();
        final BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                previousWinnerNumbers.add(new WinnerNumbers(line.split(";")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return previousWinnerNumbers;
    }

    private File getFile() {
        final ClassLoader classLoader = CSVReader.class.getClassLoader();
        return new File(classLoader.getResource(LOTTERY_DATA_SOURCE_PATH).getFile());
    }

}