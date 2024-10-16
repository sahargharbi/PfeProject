package com.smarty.system.services.impl;

import com.smarty.system.services.StatisticsService;
import com.smarty.system.dto.TransactionSumDetails;
import com.smarty.system.models.TransactionType;
import com.smarty.system.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class StatisticsServiceImpl implements StatisticsService {

    private final TransactionRepository transactionRepository;
    @Override
    public List<TransactionSumDetails> findTransactionByDate(LocalDate startDate, LocalDate endDate, Integer userId) {
        LocalDateTime start=LocalDateTime.of(startDate, LocalTime.of(0,0,0));
        LocalDateTime end=LocalDateTime.of(endDate, LocalTime.of(23,59,59));

        return transactionRepository.findSumTransactionByDate(start,end,userId);
    }

    @Override
    public BigDecimal getAccountBalance(Integer userId) {
        return transactionRepository.findAccountBalance(userId) ;
    }

    @Override
    public BigDecimal highestTransfert(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.TRANSFERT);
    }

    @Override
    public BigDecimal highestDeposit(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.DEPOSIT);
    }
}
