package com.userJPA.C1220460.UserJpaApiC1220460.service;

import com.userJPA.C1220460.UserJpaApiC1220460.dto.UserRsquestDto;
import  com.userJPA.C1220460.UserJpaApiC1220460.dto.userResponse;

import java.util.List;
public interface UserService {

    userResponse createUser(UserRsquestDto request);

     List<userResponse> getAllUsers();

    userResponse getUserById(Integer id);

    userResponse updateUser(Integer id, UserRsquestDto request);

    String deleteUser(Integer id);

}
