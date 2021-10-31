package com.ppi.salesapi.repository;

import com.ppi.salesapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserById(Long id);

    User findUserByUsername(String username);
}
