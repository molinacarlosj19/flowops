package com.flowops.auth.controller;

import com.flowops.auth.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/me")
    public UserEntity getCurrentUser() {

        return (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}