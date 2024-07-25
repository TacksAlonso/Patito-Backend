package com.enterprise.patito.users.dto;

import com.enterprise.patito.users.entity.Roles;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsersDTO {
    private String userName;
    private Roles roles;
    private LocalDateTime createDate;
    private LocalDateTime lastLogin;
}
