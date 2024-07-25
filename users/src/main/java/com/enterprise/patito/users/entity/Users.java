package com.enterprise.patito.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "USERS")
public class Users {
    @Id
    private String user_name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    private LocalDateTime create_date;
    private LocalDateTime last_login;
}
