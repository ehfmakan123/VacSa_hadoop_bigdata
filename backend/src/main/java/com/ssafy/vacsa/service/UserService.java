package com.ssafy.vacsa.service;

import com.ssafy.vacsa.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void signup(UserDto userDto) throws Exception;
    boolean idCheck(String userId) throws Exception;
    boolean nicknameCheck(String userNickname) throws Exception;
    boolean emailCheck(String userEmail) throws Exception;
    boolean passwordCheck(String userId,String userPassword) throws Exception;
    void modifyUserInfo(UserDto userDto) throws Exception;
    void deleteUserInfo(String userId) throws Exception;
    boolean login(UserDto userDto) throws Exception;
    UserDto findId(String userEmail) throws Exception;
    void modifyPassword(UserDto userDto) throws Exception;
    UserDto getUserInfo(String userId) throws  Exception;
    String getPassword(String userId) throws Exception;
}
