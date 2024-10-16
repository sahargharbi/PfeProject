package com.smarty.system.services;

import com.smarty.system.dto.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto>{
    List<TransactionDto> findAllByUserId(Integer userId);
}
