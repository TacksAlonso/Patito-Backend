package com.enterprise.patito.users.controller;

import com.enterprise.patito.users.dto.JwtResponse;
import com.enterprise.patito.users.dto.UsersDTO;
import com.enterprise.patito.users.entity.Users;
import com.enterprise.patito.users.services.TokenService;
import com.enterprise.patito.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        Users createdUsers = userService.createUser(users);
        return ResponseEntity.ok(createdUsers);
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<UsersDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UsersDTO> getUserByUserName(@PathVariable String userName) {
        UsersDTO user = userService.getUserByUserName(userName);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build(); // Not Found (404)
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestParam String userName, @RequestParam String password) {
        System.out.println("Se intenta logear usuario:" + userName);
        Users users = userService.authenticateUser(userName, password);
        if (users != null) {
            String token = tokenService.generateToken(userName, users.getRoles().name());
            System.out.println("usuario logeado exitosamente token:" + token);
            return ResponseEntity.ok(new JwtResponse(token));
        }
        System.out.println("usuario no identificado");
        return ResponseEntity.status(401).build(); // Unauthorized
    }
}
