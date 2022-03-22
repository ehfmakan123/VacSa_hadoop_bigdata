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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count; // 조회수

    // 작성자는 하나이므로 EAGER로 바로 가져오기
    @ManyToOne (fetch = FetchType.EAGER)      // 연관관계 Many = Board, User = One
    @JoinColumn(name = "userId") // FK
    private User user;

    // mappedBy가 적혀있으면 연관관계의 주인이 아님(Fk가 아님) DB에 컬럼을 만들지 않음
    // 게시판 하나에 여러개의 댓글이 달리는데 mappedBy 가 달리면 db가 이상해짐
    // 기본 패치 전략은 LAZY 전략임, CascadeType.REMOVE는 board게시물을 지울때, 댓글도 날리겠다라는 뜻임. 이걸 안 쓰면 게시물 지우면 성공알람은 뜨나, 지워지질 않음(댓글이 남아있기 때문)
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // 하나의 게시글은 여러 댓글을 가질 수 있음
    @JsonIgnoreProperties({"board"}) // 댓글 무한참조 방지가 됨 == getter 호출을 막음
    @OrderBy("id desc") // Board를 부를 때, replys_id 기준으로 내림차순으로 정렬을함 - 즉 최근 댓글이 맨 위
    private List<Comment> comment;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm")
    private LocalDate createDate; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }
}
