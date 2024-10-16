package com.smarty.system.services.impl;

import com.smarty.system.services.ContactService;
import com.smarty.system.dto.ContactDto;
import com.smarty.system.models.Contact;
import com.smarty.system.repositories.ContactRepository;
import com.smarty.system.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository repository;
    private final ObjectsValidator<ContactDto> validator;

    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact=ContactDto.toEntity(dto);
        return repository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return repository.findAll()
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return repository.findById(id)
                .map(ContactDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("No contact was found with this ID"+id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }
}
