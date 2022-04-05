package com.ssafy.vacsa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "boardId")
    private long boardId;

    @Column(name = "boardTitle", nullable = false, length = 100)
    private String boardTitle;

    @Lob // 대용량 데이터
    @Column(name = "boardContent")
    private String boardContent;

    // 작성자는 하나이므로 EAGER로 바로 가져오기
    @ManyToOne (fetch = FetchType.EAGER)      // 연관관계 Many = Board, User = One
    @JoinColumn(name = "username") // FK
    private User author;

    @OneToMany(mappedBy = "boardComment", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // 하나의 게시글은 여러 댓글을 가질 수 있음
    private List<Comment> boardComments = new ArrayList<>();;

    @CreationTimestamp  // 현재시간 입력
    private LocalDateTime boardCreateTime;

    // 게시글 수정
    public void  boardUpdate(String boardTitle, String boardContent){
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }
}
