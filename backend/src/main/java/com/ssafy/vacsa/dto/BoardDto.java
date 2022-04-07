package com.ssafy.vacsa.dto;

import com.ssafy.vacsa.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "BoardDto : 게시글정보", description = "게시글의 상세 정보를 나타낸다.")
public class BoardDto {

    public BoardDto(Board board) {     // vote entity 받아와서 vote dto로 변환. list로 딸려있는 애들은 따로 요청해서 보내야 될거 같음
        this.boardId = board.getBoardId();
        this.boardTitle = board.getBoardTitle();
        this.author=board.getAuthor().getNickname(); // 작성자 명을 닉네임으로 표시 
        this.boardContent = board.getBoardContent();
        this.boardCreateTime = board.getBoardCreateTime();
    }

    public BoardDto(long boardId, String boardTitle, String author, String boardContent, LocalDate createDate, User boardAuthor){// 게시글 검색용
        this.boardId = boardId;
        this.boardTitle =boardTitle;
        this.author = author;
    }

    public BoardDto(long boardId, String boardTitle, String author, String boardContent,  LocalDateTime boardCreateTime
            , long boardCount, long boardCommentCount){
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.author = author;
        this.boardContent = boardContent;
        this.boardCreateTime = boardCreateTime;
    }

    @ApiModelProperty(position = 1, value = "게시글 아이디", notes="자동으로 생성되므로 작성 보내지 않는다")
    private Long boardId;
    @ApiModelProperty(position = 2, value = "게시글 제목")
    private String boardTitle;
    @ApiModelProperty(position = 3, value = "게시글 내용")
    private String boardContent;
    @ApiModelProperty(position = 4, value = "게시글 생성 시간.")
    private LocalDateTime boardCreateTime;
    @ApiModelProperty(position = 5, value = "게시글에 달린 댓글들 리스트.")
    private List<Long> boardComments;
    @ApiModelProperty(position = 6, value = "게시글을 작성한 유저 닉네임")
    private String author;

}
