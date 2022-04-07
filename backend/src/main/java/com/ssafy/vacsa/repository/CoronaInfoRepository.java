package com.ssafy.vacsa.repository;

import com.ssafy.vacsa.model.CoronaInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoronaInfoRepository extends JpaRepository<CoronaInfo, Long> {
    List<CoronaInfo> findAll();
}
