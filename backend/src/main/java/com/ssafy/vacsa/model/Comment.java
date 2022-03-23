package com.ssafy.vacsa.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Long commentId;

    @Column(name = "commentContent", nullable = false, length = 1000)
    private String commentContent;

    @ManyToOne // 하나의 개시글에는 여러개의 답변이 있을 수 있음
    @JoinColumn(name = "boardId")
    private Board boardComment;

    @ManyToOne
    @JoinColumn(name =  "username")
    private User commentUser;

    @Column(name = "commentCreateTime")
    private LocalDateTime commentCreateTime;
}
