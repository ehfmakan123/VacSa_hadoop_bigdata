package com.ssafy.vacsa.service;

import com.ssafy.vacsa.dto.CoronaInfoDto;
import com.ssafy.vacsa.model.CoronaInfo;
import com.ssafy.vacsa.repository.CoronaInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CoronaInfoServiceImpl implements CoronaInfoService {
    private final CoronaInfoRepository coronaInfoRepository;

    @Override
    public List<CoronaInfoDto> getWordList() throws Exception {
        List<CoronaInfo> list = coronaInfoRepository. findAll();
        List<CoronaInfoDto> dtoList = new ArrayList<>();
        for(CoronaInfo entity: list) {
            if(entity.getWord().length() < 2 || !entity.getSearch_word().equals("코로나")) continue;
            CoronaInfoDto dto = new CoronaInfoDto(entity);
            dtoList.add(dto);
        }

        return dtoList;
    }
}
