package com.ssafy.vacsa.dto;

import com.ssafy.vacsa.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String uid;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private LocalDate createDate;
    private String gender;

    @ApiModelProperty(value = "유저가 생성한 board id목록", notes="id를 이용해서 Board 테이블에서 검색")
    private List<Long> createBoards;
    @ApiModelProperty(value = "유저가 작성한 comment id목록", notes="id를 이용해서 Comment 테이블에서 검색")
    private List<Long> comments;

    public UserDto(User user){
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.createDate = user.getCreateDate();
        this.gender = user.getGender();
    }

    public UserDto(String username, String nickname){
        this.username = username;
        this.nickname = nickname;
    }
}
