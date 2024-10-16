package com.smarty.system.services.impl;

import com.smarty.system.services.AddressService;
import com.smarty.system.dto.AddressDto;
import com.smarty.system.models.Address;
import com.smarty.system.repositories.AddressRepository;
import com.smarty.system.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repository;
    private ObjectsValidator<AddressDto>validator;
    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address=AddressDto.toEntity(dto);
        return repository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {

        return repository.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {

        return repository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("No address with the ID"+id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }
}
