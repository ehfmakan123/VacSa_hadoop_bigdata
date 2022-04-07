package com.ssafy.vacsa.dto;

import com.ssafy.vacsa.model.CoronaInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoronaInfoDto {

    public CoronaInfoDto(CoronaInfo coronaInfo) {
        this.id = coronaInfo.getId();
        this.search_word = coronaInfo.getSearch_word();
        this.word = coronaInfo.getWord();
        this.count = coronaInfo.getCount();
    }

    @ApiModelProperty(position = 1, value = "WordCount 아이디")
    private Long id;
    private String search_word;
    private String word;
    private Long count;
}
