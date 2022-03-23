package com.ssafy.vacsa.service;

import com.ssafy.vacsa.dto.EmailTokenDto;
import com.ssafy.vacsa.model.EmailConfirmationToken;
import com.ssafy.vacsa.repository.EmailConfirmationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailConfirmationServiceImpl implements EmailConfirmationService {
    private final EmailConfirmationTokenRepository emailConfirmationTokenRepository;
    private final EmailSenderService emailSenderService;
    private static final long EMAIL_TOKEN_EXPIRATION_TIME = 5L; //5분

    // 이메일 인증을 위해 이메일 생성 메서드
    @Override
    public String createEmailConfirmationToken(String userEmail) throws Exception {
        EmailConfirmationToken emailConfirmationToken = EmailConfirmationToken.builder()
                .userEmail(userEmail)
                .createTime(LocalDateTime.now())
                .expirationDate(LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME))
                .exprired(false)
                .build();
        emailConfirmationTokenRepository.save(emailConfirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setSubject("Vacsa 회원가입 이메일 인증");
        mailMessage.setText("인증 번호: "+emailConfirmationToken.getEmailTokenId());
        emailSenderService.sendEmail(mailMessage);

        return emailConfirmationToken.getEmailTokenId();
    }

    @Transactional
    @Override
    public boolean isValidToken(String token) throws Exception {
        Optional<EmailConfirmationToken> emailConfirmationToken = emailConfirmationTokenRepository.findById(token);
        EmailTokenDto emailTokenDto = null;
        if(emailConfirmationToken.isPresent()){
            emailTokenDto = new EmailTokenDto(emailConfirmationToken.get());
            if(emailTokenDto.getExpired()){// 이미 만료됨
               return false;
            }
            EmailConfirmationToken emailConfirmationTokenExpired = EmailConfirmationToken.builder()
                    .emailTokenId(token)
                    .expirationDate(emailTokenDto.getExpirationDate())
                    .userEmail(emailTokenDto.getUserEmail())
                    .createTime(emailTokenDto.getCreateTime())
                    .exprired(true)
                    .build();
            if(LocalDateTime.now().isAfter(emailTokenDto.getExpirationDate())){// 5분이 지남
                emailConfirmationTokenRepository.save(emailConfirmationTokenExpired);
                return false;
            }
            if(token.equals(emailTokenDto.getEmailTokenId())){// 토큰이 유효하다.
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isValidEmail(String token,String userEmail) throws Exception {
        Optional<EmailConfirmationToken> emailConfirmationToken = emailConfirmationTokenRepository.findById(token);
        EmailTokenDto emailTokenDto = null;
        if(emailConfirmationToken.isPresent()){
            emailTokenDto = new EmailTokenDto(emailConfirmationToken.get());
            if(emailTokenDto.getUserEmail().equals(userEmail)){ // 이메일유저와 토큰 정보가 일치하는지
                return true;
            }
        }
        return false;
    }

}
