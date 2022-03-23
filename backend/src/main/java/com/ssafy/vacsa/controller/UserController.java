package com.ssafy.vacsa.controller;


import com.ssafy.vacsa.dto.UserDto;
import com.ssafy.vacsa.jwt.JwtTokenProvider;
import com.ssafy.vacsa.service.EmailConfirmationService;
import com.ssafy.vacsa.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailConfirmationService emailConfirmationService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @ApiOperation(value = "회원가입",notes="회원 정보를 입력한다.")
    @PostMapping("/signup")
    public ResponseEntity<String> signUp (@RequestBody @ApiParam(value = "회원가입시 필요한 회원정보", required = true) UserDto userDto )throws Exception {
        HttpStatus status = null;
        String message = "";
        try {
            userService.signup(userDto);
            status = HttpStatus.OK;
            message = SUCCESS;
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = FAIL;
        }
        return new ResponseEntity<String>(message, status);
    }

    @ApiOperation(value ="아이디 중복검사")
    @GetMapping("/idcheck")
    public ResponseEntity<Boolean> idCheck(@RequestParam @ApiParam(value = "중복된 아이디가 있는지 확인") String userId) throws Exception{
        boolean checkflag = false;
        HttpStatus status = null;
        try {
            if(userService.idCheck((userId))){
                checkflag= true;
            }
            status = HttpStatus.OK;
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Boolean>(checkflag,status);
    }

    @ApiOperation(value = "닉네임중복체크")
    @GetMapping("/nickcheck")
    public ResponseEntity<Boolean> nicknameCheck(@RequestParam @ApiParam(value = "중복된 닉네임이 있는지 확인") String userNickname) throws Exception{
        boolean checkflag = false;
        HttpStatus status = null;
        try {
            if(userService.nicknameCheck((userNickname))){
                checkflag= true;
            }
            status = HttpStatus.OK;
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Boolean>(checkflag,status);
    }

    @ApiOperation(value = "현재 비밀 번호 확인")
    @GetMapping("/passwordcheck")
    public ResponseEntity<Boolean> passwordCheck(@RequestParam String userId, @RequestParam String password)throws Exception{
        boolean checkflag = false;
        HttpStatus status = null;
        try {
            if(userService.passwordCheck(userId,password)){
                checkflag = true;
            }
            status = HttpStatus.OK;
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(checkflag,status);
    }

    @ApiOperation(value = "이메일중복체크")
    @GetMapping("/emailcheck")
    public ResponseEntity<Boolean> emailCheck(@RequestParam @ApiParam(value = "중복된 이메일이 있는지 확인") String userEmail) throws Exception {
        boolean checkflag = false;
        HttpStatus status = null;
        try {
            if(userService.emailCheck(userEmail)){
                checkflag= true;
            }
            status = HttpStatus.OK;
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Boolean>(checkflag,status);
    }

    // 사용자 회원가입중 이메일 인증 버튼 클릭시 링크를 사용자 이메일로 보내준다.
    @ApiOperation(value = "회원가입 이메일 인증")
    @GetMapping("/confirmemail")
    public ResponseEntity<Map<String,Object>> sendEmail(@RequestParam @ApiParam(value = "이메일 정보") String userEmail)throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            if(userService.emailCheck(userEmail)){
                emailConfirmationService.createEmailConfirmationToken(userEmail);
                resultMap.put("message",SUCCESS);
            }else{
                resultMap.put("message", FAIL);
            }
            status = HttpStatus.ACCEPTED;
        }catch (Exception e){
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap,status);
    }

    @ApiOperation(value = "이메일 인증 Token검사")
    @GetMapping("/emailtoken")
    public ResponseEntity<Map<String,Object>> confirmEmail(@RequestParam @ApiParam(value = "이메일 인증 토큰 정보") String token, @RequestParam @ApiParam(value = "유저 이메일") String userEmail)throws Exception{
        HashMap<String,Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            if(emailConfirmationService.isValidToken(token) && emailConfirmationService.isValidEmail(token,userEmail)){
                resultMap.put("isValid",true);
            }else if(emailConfirmationService.isValidToken(token)){
                resultMap.put("isValid",false);
            }else{
                resultMap.put("isValid",false);
            }
            resultMap.put("userEmail",userEmail);
            resultMap.put("message",SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            resultMap.put("message",FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap,status);
    }

    @ApiOperation(value ="회원정보 수정", notes = "간단한 회원정보 수정 category는 따로 수정")
    @PutMapping
    public ResponseEntity<String> modifyUserInfo(@RequestBody @ApiParam(value ="수정한 회원정보", required = true) UserDto userDto) throws Exception{
        logger.info(userDto.getUsername()+" " +userDto.getUid());
        String message = "";
        HttpStatus status = null;
        try {
            userService.modifyUserInfo(userDto);
            message = SUCCESS;
            status = HttpStatus.OK;
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = FAIL;
        }
        return new ResponseEntity<>(message,status);
    }

    @ApiOperation(value ="회원정보 삭제", notes = "회원정보 삭제")
    @DeleteMapping
    public ResponseEntity<String> deleteUserInfo(@RequestParam @ApiParam(value = "삭제할 유저 id", required = true)String userId)throws Exception{

        userService.deleteUserInfo(userId);

        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }

    // 사용자 로그인 Jwt토큰 api 통신
    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody UserDto userDto){
        Map<String,Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            if(userService.login(userDto)){
                String token = jwtTokenProvider.createToken("userId",userDto.getUsername());
                resultMap.put("accessToken",token);
                resultMap.put("userId",userDto.getUsername());
                resultMap.put("nickname",userService.getUserInfo(userDto.getUsername()).getNickname());
                resultMap.put("email",userService.getUserInfo(userDto.getUsername()).getEmail());
                resultMap.put("gender", userService.getUserInfo(userDto.getUsername()).getGender());
                resultMap.put("createday", userService.getUserInfo(userDto.getUsername()).getCreateDate());
                resultMap.put("message",SUCCESS);
            }else{
                resultMap.put("message",FAIL);
            }
            status = HttpStatus.ACCEPTED;
        }catch (Exception e){
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String,Object>>(resultMap,status);
    }

    @ApiOperation(value = "아이디 찾기")
    @GetMapping("/findid")
    public ResponseEntity<Map<String,Object>> findId(@RequestParam @ApiParam(value = "이메일 정보") String userEmail) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            if(!userService.emailCheck(userEmail)){//이메일이 존재하는 경우
                emailConfirmationService.createEmailConfirmationToken(userEmail);
                resultMap.put("message",SUCCESS);
                resultMap.put("userId",userService.findId(userEmail));
            }else{
                resultMap.put("message", FAIL);
            }
            status = HttpStatus.ACCEPTED;
        }catch (Exception e){
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap,status);
    }

    @ApiOperation(value ="비밀번호 수정")
    @PutMapping("/modifypass")
    public ResponseEntity<String> modifyPassword(@RequestBody @ApiParam(value ="수정한 비밀번호", required = true) UserDto userDto) throws Exception{
        String message = "";
        HttpStatus status = null;
        try{
            userService.modifyPassword(userDto);
            message = SUCCESS;
            status = HttpStatus.OK;
        }catch (Exception e){
            message = FAIL;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(message,status);
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<Map<String,Object>> getInfoUser(@PathVariable String userId)throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        String message = "";
        HttpStatus status = null;
        try {
            UserDto userDto = userService.getUserInfo(userId);
            resultMap.put("userId",userDto.getUsername());
            resultMap.put("nickname",userService.getUserInfo(userDto.getUsername()).getNickname());
            resultMap.put("email",userService.getUserInfo(userDto.getUsername()).getEmail());
            resultMap.put("gender", userService.getUserInfo(userDto.getUsername()).getGender());
            resultMap.put("createday", userService.getUserInfo(userDto.getUsername()).getCreateDate());
            resultMap.put("message",SUCCESS);
            status = HttpStatus.OK;
        }catch (Exception e){
            resultMap.put("message",FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap,status);
    }

}
