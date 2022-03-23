package com.ssafy.vacsa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class EmailConfirmationToken {
    // Email을 인증 할때 발행하는 토큰을 저장하는 Table
    // 만료시간을 만들어서 만료시간 이후에 인증불가 처리를 할 예정이다.

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    private String emailTokenId;

    @Column
    private LocalDateTime expirationDate;

    @Column
    private Boolean exprired;

    @Column
    private String userEmail;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;

}
