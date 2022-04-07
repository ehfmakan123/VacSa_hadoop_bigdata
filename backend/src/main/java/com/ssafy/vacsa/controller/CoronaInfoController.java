package com.ssafy.vacsa.controller;

import com.ssafy.vacsa.dto.CoronaInfoDto;
import com.ssafy.vacsa.service.CoronaInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wordcloud")
@RequiredArgsConstructor
public class CoronaInfoController {
    private final CoronaInfoService wordCountService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @GetMapping
    public ResponseEntity<List<CoronaInfoDto>> getWordList() throws Exception {
        List<CoronaInfoDto> list = wordCountService.getWordList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
