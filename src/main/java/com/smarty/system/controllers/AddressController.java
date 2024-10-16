package com.smarty.system.controllers;

import com.smarty.system.dto.AddressDto;
import com.smarty.system.services.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adresses")
@RequiredArgsConstructor
@Tag(name= "address")

public class AddressController {
    private final AddressService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody AddressDto addressDto
            ) {
        return ResponseEntity.ok(service.save(addressDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{address-id}")
    public ResponseEntity<AddressDto> findById(
            @PathVariable("address-id")Integer addressId
    ){
        return ResponseEntity.ok(service.findById(addressId));
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("address-id") Integer addressId
    ){
        service.delete(addressId);
        return ResponseEntity.accepted().build();
    }
}
