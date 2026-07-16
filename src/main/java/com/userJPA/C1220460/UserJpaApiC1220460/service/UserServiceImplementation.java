package com.userJPA.C1220460.UserJpaApiC1220460.service;

import com.userJPA.C1220460.UserJpaApiC1220460.dto.UserRsquestDto;
import com.userJPA.C1220460.UserJpaApiC1220460.dto.userResponse;
import com.userJPA.C1220460.UserJpaApiC1220460.entity.User;
import com.userJPA.C1220460.UserJpaApiC1220460.exception.ExistingEmailExceptions;
import com.userJPA.C1220460.UserJpaApiC1220460.exception.UsernotFoundExcetions;
import com.userJPA.C1220460.UserJpaApiC1220460.repository.UserRep;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImplementation implements UserService {
    private final UserRep userRepository;

    public UserServiceImplementation(UserRep userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public userResponse createUser(UserRsquestDto request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ExistingEmailExceptions("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());

        User savedUser = userRepository.save(user);

        return new userResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getAddress()
        );
    }

    @Override
    public List<userResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> new userResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getAddress()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public userResponse getUserById(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernotFoundExcetions("User not found"));

        return new userResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAddress()
        );
    }

    @Override
    public userResponse updateUser(Integer id, UserRsquestDto request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernotFoundExcetions("User not found"));

        userRepository.findByEmail(request.getEmail())
                .ifPresent(existingUser -> {
                    if (!existingUser.getId().equals(id)) {
                        throw new ExistingEmailExceptions("Email already exists");
                    }
                });

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());

        User updatedUser = userRepository.save(user);

        return new userResponse(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getAddress()
        );
    }

    @Override
    public String deleteUser(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernotFoundExcetions("User not found"));

        userRepository.delete(user);

        return "User deleted successfully";
    }


}
