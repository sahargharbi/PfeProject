package com.smarty.system.repositories;

import com.smarty.system.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
    List<Contact> findAllByUserId(Integer userId);
}
