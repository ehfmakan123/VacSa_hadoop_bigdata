package com.ssafy.vacsa.controller;

import com.ssafy.vacsa.dto.*;
import com.ssafy.vacsa.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    
    @ApiOperation(value = "게시글 생성", notes = "게시글 정보를 입력한다.")
    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createVote(@RequestBody BoardDto boardDto) throws Exception {
        Long boardId = boardService.create(boardDto);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("message",SUCCESS);
        resultMap.put("boardId",boardId);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);  // status 200과 success라는 문자열을 반환
    }

    @ApiOperation(value = "게시글 삭제", notes = "해당 게시글을 삭제한다.")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable @ApiParam(value = "게시글의 id", required = true) Long boardId) throws Exception {
        boardService.delete(boardId);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 상세 페이지", notes = "해당 게시글 상세정보를 불러온다.")
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDto> detailVote(@PathVariable @ApiParam(value = "게시글의 id", required = true) Long boardId,@RequestParam @ApiParam(value = "로그인 유저 id", required = true) String username) throws Exception {
        return new ResponseEntity<BoardDto>(boardService.detail(boardId,username), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 전체목록")
    @GetMapping
    public ResponseEntity<List<BoardDto>> getVoteList(@RequestParam(required = false) @ApiParam(value = "로그인한 유저 아이디") String username) throws Exception{
        List<BoardDto> list = boardService.getBoardList(username);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
