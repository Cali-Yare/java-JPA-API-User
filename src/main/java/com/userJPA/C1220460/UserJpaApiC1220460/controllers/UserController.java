package com.userJPA.C1220460.UserJpaApiC1220460.controllers;
import com.userJPA.C1220460.UserJpaApiC1220460.dto.userResponse;
import com.userJPA.C1220460.UserJpaApiC1220460.dto.UserRsquestDto;
import com.userJPA.C1220460.UserJpaApiC1220460.service.UserService;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public userResponse createUser(@Valid @RequestBody UserRsquestDto request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<userResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public userResponse getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public userResponse updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UserRsquestDto request) {

        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

}
