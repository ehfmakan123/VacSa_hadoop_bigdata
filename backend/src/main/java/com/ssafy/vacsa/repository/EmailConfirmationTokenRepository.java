package com.ssafy.vacsa.repository;

import com.ssafy.vacsa.model.EmailConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailConfirmationTokenRepository extends JpaRepository<EmailConfirmationToken,String> {

}
