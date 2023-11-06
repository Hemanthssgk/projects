package com.BlogApplication.BlogApplicaiton.repositories;

import com.BlogApplication.BlogApplicaiton.repositories.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
