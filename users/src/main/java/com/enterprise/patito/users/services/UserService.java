package com.enterprise.patito.users.services;

import com.enterprise.patito.users.dto.UsersDTO;
import com.enterprise.patito.users.entity.Users;
import com.enterprise.patito.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UsersDTO> getAllUsers() {
        List<Users> usersList = userRepository.findAll();

        return usersList.stream()
                .map(user -> {
                    UsersDTO dto = new UsersDTO();
                    dto.setUserName(user.getUser_name());
                    dto.setRoles(user.getRoles());
                    dto.setCreateDate(user.getCreate_date());
                    dto.setLastLogin(user.getLast_login());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public UsersDTO getUserByUserName(String userName){

        Optional<Users> optionalUser = userRepository.findById(userName);

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            UsersDTO dto = new UsersDTO();
            dto.setUserName(user.getUser_name());
            dto.setRoles(user.getRoles());
            dto.setCreateDate(user.getCreate_date());
            dto.setLastLogin(user.getLast_login());
            return dto;
        }

        return null;

    }

    public Users createUser(Users users) {
        users.setCreate_date(LocalDateTime.now());
        return userRepository.save(users);
    }

    public Users authenticateUser(String userName, String password) {
        Users users = userRepository.findById(userName).orElse(null);
        if (users != null && users.getPassword().equals(password)) {
            users.setLast_login(LocalDateTime.now());
            userRepository.save(users);
            return users;
        }
        return null;
    }

}
