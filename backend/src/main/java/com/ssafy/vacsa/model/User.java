package com.ssafy.vacsa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db 넘버링 전략 사용
    private int id; // 시퀀스 auto_increment

    @Column(nullable = false, length = 30, unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50,  unique = true)
    private String email;

    @Column(nullable = true, length = 50,  unique = true)
    private String nickname;

//    @ColumnDefault("'user'")
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 써서, ADMIN,USER로 형이 강제

//    private String oauth; // kakao, google ... 회원수정을 막기위해 필요함

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }
}
