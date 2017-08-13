package com.manager.lottery.LotteryManager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;

@Document(collection = "lotteryData")
public class WinnerNumbers {

    @Id
    private String mongoId;

    @Field("id")
    private Long id;

    private int year;

    private String date;

    private String prize;

    private int winnersCount;

    private int[] numbers;

    public WinnerNumbers() {
    }

    public WinnerNumbers(String[] line) {
        this.year = Integer.parseInt(line[0]);
        this.date = line[2];
        this.winnersCount = Integer.parseInt(line[3]);
        this.prize = line[4];
        this.numbers = new int[]{Integer.parseInt(line[11]), Integer.parseInt(line[12]), Integer.parseInt(line[13]),
                Integer.parseInt(line[14]), Integer.parseInt(line[15])};
    }

    public String getMongoId() {
        return mongoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public int getWinnersCount() {
        return winnersCount;
    }

    public void setWinnersCount(int winnersCount) {
        this.winnersCount = winnersCount;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WinnerNumbers that = (WinnerNumbers) o;

        if (year != that.year) return false;
        if (winnersCount != that.winnersCount) return false;
        if (mongoId != null ? !mongoId.equals(that.mongoId) : that.mongoId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return (prize != null ? !prize.equals(that.prize) : that.prize != null) ? false : Arrays.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        int result = mongoId != null ? mongoId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (prize != null ? prize.hashCode() : 0);
        result = 31 * result + winnersCount;
        result = 31 * result + Arrays.hashCode(numbers);
        return result;
    }

    @Override
    public String toString() {
        return "WinnerNumbers{" +
                "mongoId='" + mongoId + '\'' +
                ", id=" + id +
                ", year=" + year +
                ", date='" + date + '\'' +
                ", prize=" + prize +
                ", winnersCount=" + winnersCount +
                ", numbers=" + Arrays.toString(numbers) +
                '}';
    }
}
