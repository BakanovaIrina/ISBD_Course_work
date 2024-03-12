package com.example.backend4.repository;

import com.example.backend4.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String login);

    public User findByUsernameAndPassword(String login, String password);


}
