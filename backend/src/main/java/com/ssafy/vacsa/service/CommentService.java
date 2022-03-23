package com.ssafy.vacsa.service;

import com.ssafy.vacsa.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void create(CommentDto commentDto) throws Exception;
    List<CommentDto> getComments(Long boardId) throws Exception;

    void delete(Long commentId) throws Exception;
    void modify(CommentDto commentDto) throws Exception;
}
