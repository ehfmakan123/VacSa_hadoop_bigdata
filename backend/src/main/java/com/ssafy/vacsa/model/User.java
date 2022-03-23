package com.ssafy.vacsa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2" , strategy = "uuid2")
    @Column(length = 36)
    private String uid;

    @Column(length = 16 , name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(length = 16 , name = "nickname")
    private String nickname;

    @Column(length = 45 , name = "email")
    private String email;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm")
    private LocalDate createDate; // 날짜

    @Column(name = "gender")
    private String  gender;

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }

    @OneToMany(mappedBy = "commentUser",cascade = CascadeType.REMOVE)
    private List<Comment> userComments = new ArrayList<>();

    // user안에 update메소드를 만들어서 request를 param으로 바꿔서
    // setter => update
    public void passwordUpdate(String password){// builder와 사용 목적이 같다.
        this.password = password;
    }

    public void nickNameUpdate(String nickname) {
        this.nickname = nickname;
    }
    // Jwt 토큰을 위해 UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
