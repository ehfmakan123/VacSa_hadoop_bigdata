package com.ssafy.vacsa.dto;

import com.ssafy.vacsa.model.EmailConfirmationToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailTokenDto {
    private String emailTokenId;
    private LocalDateTime expirationDate;
    private Boolean expired;
    private String userEmail;
    private LocalDateTime createTime;

    public EmailTokenDto(EmailConfirmationToken emailConfirmationToken){
        this.emailTokenId = emailConfirmationToken.getEmailTokenId();
        this.expirationDate = emailConfirmationToken.getExpirationDate();
        this.expired = emailConfirmationToken.getExprired();
        this.userEmail = emailConfirmationToken.getUserEmail();
        this.createTime = emailConfirmationToken.getCreateTime();
    }
}
