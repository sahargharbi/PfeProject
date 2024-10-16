package com.smarty.system.services.impl;

import com.smarty.system.services.AccountService;
import com.smarty.system.dto.AccountDto;
import com.smarty.system.exceptions.OperationNonPermittedException;
import com.smarty.system.models.Account;
import com.smarty.system.repositories.AccountRepository;
import com.smarty.system.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto>validator;

    @Override
    public Integer save(AccountDto dto) {

        validator.validate(dto);
        Account account=AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();
        if (userHasAlreadyAnAccount && account.getUser().isActive()){
            throw new OperationNonPermittedException(
                    "the selected user has already an active account",
                    "Create account" ,
                    "Account service" ,
                    "Account creation"
            );
        }
        //generate random IBAN when creating new account else do not update the iban
        if(dto.getId() == null){
            account.setIban(generateRandomIban());
        }
        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {

        return repository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {

        return repository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No account was found with the ID:"+id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }
    public String generateRandomIban(){
        //todo generate an iban
        String iban= Iban.random(CountryCode.DE).toFormattedString();
        //if exists generate new random iban
        boolean ibanExists= repository.findByIban(iban).isPresent();
        //if not exist return generated iban
        if(ibanExists){
            generateRandomIban();
        }

        return iban;
    }


}
