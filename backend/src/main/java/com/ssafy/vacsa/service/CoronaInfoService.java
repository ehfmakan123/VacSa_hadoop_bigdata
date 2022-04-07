package com.ssafy.vacsa.service;

import com.ssafy.vacsa.dto.CoronaInfoDto;

import java.util.List;

public interface CoronaInfoService {
    List<CoronaInfoDto> getWordList() throws Exception;
}
