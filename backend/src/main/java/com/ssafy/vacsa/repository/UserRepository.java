package com.ssafy.vacsa.repository;

import com.ssafy.vacsa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Integer> {

}
