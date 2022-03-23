package com.ssafy.vacsa.service;

import com.ssafy.vacsa.model.*;
import com.ssafy.vacsa.dto.*;
import com.ssafy.vacsa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    @Override
    public void create(CommentDto commentDto) throws Exception {    // 댓글 생성

        User commentUser = userRepository.findByUsername(commentDto.getCommentUser()).get();    // user id로 user 객체 찾아오기
        Board boardComment = boardRepository.findByBoardId(commentDto.getBoardComment()).get();    // board id로 board 객체 찾아오기
        Comment comment = Comment.builder()
                .commentUser(commentUser)
                .boardComment(boardComment)
                .commentContent(commentDto.getCommentContent())
                .commentCreateTime(LocalDateTime.now())   // 현재시간 입력
                .build();
        commentRepository.save(comment);    // DB에 reply 정보 저장
    }


    @Override
    public List<CommentDto> getComments(Long boardId) throws Exception {    // 해당 투표의 댓글들 불러오기

        Board board = boardRepository.findById(boardId).get();  // 아이디를 이용하여 해당 투표를 가져옴
        List<Comment> comments = commentRepository.getAllByBoardComment(board);  // 투표의 댓글 엔티티 불러옴
        List<CommentDto> dtoList = new ArrayList<>();     // 리턴할 reply dto list 생성

        for(Comment comment : comments) {    // reply 엔티티를 reply dto로 변환해서 list에 추가
            dtoList.add(new CommentDto(comment));
        }

        return dtoList;
    }

    @Override
    public void delete(Long commentId) throws Exception {   // 해당 댓글의 아이디를 이용해서 댓글 삭제
        Optional<Comment> comment = commentRepository.findById(commentId);
        commentRepository.delete(comment.get());
    }



    @Override
    public void modify(CommentDto commentDto) throws Exception {    // 받아온 댓글의 정보의 아이디를 이용하여 댓글 정보 수정
        Comment comment = commentRepository.findById(commentDto.getCommentId()).get();
        
        Comment newComment = Comment.builder()
                .commentId(comment.getCommentId())
                .commentUser(comment.getCommentUser())
                .boardComment(comment.getBoardComment())
                .commentContent(commentDto.getCommentContent())   // 내용만 dto에서 받아옴
                .commentCreateTime(comment.getCommentCreateTime())
                .build();
        
        commentRepository.save(newComment);     // 변경된 정보 저장
    }
}
