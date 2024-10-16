package com.smarty.system.services;

import com.smarty.system.dto.AuthenticationRequest;
import com.smarty.system.dto.AuthenticationResponse;
import com.smarty.system.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{
    Integer validateAccount(Integer id);
    Integer invalidateAccount(Integer id);

    AuthenticationResponse register(UserDto user);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
