package com.smarty.system.services;

import com.smarty.system.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>{
    List<ContactDto> findAllByUserId(Integer userId);
}
