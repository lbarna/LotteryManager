package com.manager.lottery.LotteryManager.repository;

import com.manager.lottery.LotteryManager.model.WinnerNumbers;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LotteryRepository extends MongoRepository<WinnerNumbers, Long> {

}
