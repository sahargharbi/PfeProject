package com.smarty.system.services;

import com.smarty.system.dto.TransactionSumDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface StatisticsService {
    List<TransactionSumDetails> findTransactionByDate(LocalDate startDate, LocalDate endDate, Integer userId);
    BigDecimal getAccountBalance(Integer userId);
    BigDecimal highestTransfert(Integer userId);
    BigDecimal highestDeposit(Integer userId);

}
