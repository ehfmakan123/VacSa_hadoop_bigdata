package com.ssafy.vacsa.controller;

import com.ssafy.vacsa.dto.CommentDto;
import com.ssafy.vacsa.model.*;
import com.ssafy.vacsa.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private static final String SUCCESS = "success";

    @ApiOperation(value = "댓글생성", notes = "댓글 정보를 입력한다.")
    @PostMapping("/create")
    public ResponseEntity<String> createReply(@RequestBody @ApiParam(value = "댓글 정보.", required = true) CommentDto commentDto) throws Exception {
        commentService.create(commentDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);  // status 200과 success라는 문자열을 반환
    }

    @ApiOperation(value = "댓글 목록", notes = "해당 게시글의 모든 댓글 정보를 반환", response = List.class)
    @GetMapping("/{boardId}")
    public ResponseEntity<List<CommentDto>> getReplies(@PathVariable("boardId") @ApiParam(value = "댓글을 얻기 위한 게시글의 아이디", required = true) Long boardId) throws Exception {
        return new ResponseEntity<List<CommentDto>>(commentService.getComments(boardId), HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 삭제", notes = "해당 댓글을 삭제한다.")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteReply(@PathVariable("commentId") @ApiParam(value = "댓글 아이디", required = true) Long commentId) throws Exception {
        commentService.delete(commentId);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 수정", notes = "수정할 댓글 정보를 입력한다.")
    @PutMapping
    public ResponseEntity<String> modifyComment(@RequestBody @ApiParam(value = "수정할 댓글 정보", required = true) CommentDto commentDto) throws Exception {
        commentService.modify(commentDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
}
