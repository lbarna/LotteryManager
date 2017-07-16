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

    private int[] numbers;

    public String getMongoId() {
        return mongoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        if (mongoId != null ? !mongoId.equals(that.mongoId) : that.mongoId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return Arrays.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        int result = mongoId != null ? mongoId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(numbers);
        return result;
    }

    @Override
    public String toString() {
        return "WinnerNumbers{" +
                "id=" + id +
                ", numbers=" + Arrays.toString(numbers) +
                '}';
    }
}
