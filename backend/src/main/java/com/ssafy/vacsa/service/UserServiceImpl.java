package com.ssafy.vacsa.service;

import com.ssafy.vacsa.dto.UserDto;
import com.ssafy.vacsa.model.User;
import com.ssafy.vacsa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void signup(UserDto userDto) throws Exception {

        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .email(userDto.getEmail())
                .createDate(userDto.getCreateDate())
                .gender(userDto.getGender())
                .build();

        // User에 user 정보 저장
        userRepository.save(user);
    }

    @Override
    public boolean idCheck(String username) throws Exception {
        if(userRepository.findByUsername(username).isPresent()){//아이디가 있는경우
            return false;
        }else{//아이디가 없는경우
            return true;
        }
    }

    @Override
    public boolean nicknameCheck(String nickname) throws Exception {
        if(userRepository.findByNickname(nickname).isPresent()){//닉네임이 있는경우
            return false;
        }else{//닉네임이 없는경우
            return true;
        }
    }

    @Override
    public boolean emailCheck(String email) throws Exception {
        if(userRepository.findByEmail(email).isPresent()){//이메일이 있는경우
            return false;
        }else{//이메일이 없는경우
            return true;
        }
    }

    @Override
    public boolean passwordCheck(String username, String userPassword) throws Exception {
        if(userRepository.findByUsernameAndPassword(username,userPassword).isPresent()){//비밀번호 일치한경우
            return true;
        }else{
            return false;
        }
    }

    //회원정보 수정
    @Override
    public void modifyUserInfo(UserDto userDto) throws Exception{
        User usercur = userRepository.findByUsername(userDto.getUsername()).get();

        usercur.nickNameUpdate(userDto.getNickname());
        // User에 user 정보 저장
        userRepository.save(usercur);
    }

    @Override
    public void deleteUserInfo(String username) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);

        userRepository.delete(user.get());
    }

    @Override
    public boolean login(UserDto userDto) throws Exception {
        if(userDto.getUsername() == null || userDto.getPassword()==null){
            return false;
        }else{
            String dbPassword = getPassword(userDto.getUsername());
            if(!passwordEncoder.matches(userDto.getPassword(),dbPassword)){
                return false;
            }else{
                return true;
            }
        }

    }

    @Override
    public String findId(String email) throws Exception {
        String userName = userRepository.findByEmail(email).get().getUsername();
        return userName;
    }

    // 비밀번호 변경
    @Transactional
    @Override
    public void modifyPassword(UserDto userDto) throws Exception {
        User user = userRepository.findByUsername(userDto.getUsername()).get();
        user.passwordUpdate(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDto getUserInfo(String username) throws Exception {
        User user = userRepository.findByUsername(username).get();
        UserDto userDto = new UserDto(user);
        return userDto;
    }

    @Override
    public String getPassword(String username) throws Exception {
        User user = userRepository.findByUsername(username).get();
        String password = user.getPassword();
        return password;
    }

}
