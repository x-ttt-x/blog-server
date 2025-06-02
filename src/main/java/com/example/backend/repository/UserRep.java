package com.example.backend.repository;

import com.example.backend.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRep extends JpaRepository<User, Integer> {
}
