package com.ssafy.vacsa.repository;

import com.ssafy.vacsa.model.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByAuthor(User user);  //유저가 만든 게시글 반환
    long countAllByAuthor(User user);       //유저가 만든 게시글 개수
    Optional<Board> findByBoardId(Long boardComment);

    List<Board> findByOrderByBoardCreateTimeDesc();           //게시글 최신순 정렬


}
