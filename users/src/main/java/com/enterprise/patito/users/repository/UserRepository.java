package com.enterprise.patito.users.repository;

import com.enterprise.patito.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String> {

}
