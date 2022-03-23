package com.ssafy.vacsa.repository;

import com.ssafy.vacsa.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getAllByBoardComment(Board comment);
    long countAllByBoardComment(Comment comment);
}
