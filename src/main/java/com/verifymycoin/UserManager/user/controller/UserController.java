package com.verifymycoin.UserManager.user.controller;


import com.verifymycoin.UserManager.user.domain.User;
import com.verifymycoin.UserManager.user.domain.UserDto;
import com.verifymycoin.UserManager.user.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Api(tags = "users")
@RequiredArgsConstructor
@RequestMapping("/user")
//@CrossOrigin("http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 403, message = "승인 거절"),
    })
    public ResponseEntity<UserDto> signin(@ApiParam("Signup User")
                                         @RequestHeader String Authorization, @RequestBody String idToken) throws Exception {
//        return ResponseEntity.ok(new UserDto("6145f518848f3a63c6a75fa1", "test", "/url", "test given name" , "test family name", "email@naver.com"));
        return ResponseEntity.ok(transToUserDto(userService.signin(Authorization, idToken)));
    }



   @GetMapping("/userInfo/{id}")
    @ApiOperation(value = "${UserController.userInfo}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 403, message = "승인 거절"),
    })
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String id) throws Exception {

       return ResponseEntity.ok(transToUserDto(userService.getById(id)));
    }


    @GetMapping("/allUserInfo")
    public ResponseEntity<List<User>> findAllUser(@RequestHeader String userId ) {

        log.info("getAllUserInfo API {}", userId);
//        log.info("request header value : {}", header);
//        return ResponseEntity.ok(
//                userService
//                        .findAll()
//                        .stream().map(this::transToUserDto)
//                        .collect(Collectors.toList()));
//        TODO : USER -> USERDto change
        return ResponseEntity.ok(userService.findAll().stream().collect(Collectors.toList()));
    }


//    TODO : status로 변경
    @PostMapping("/withdrawal")
    @ApiOperation(value = "${UserController.withdrawal}")
    public ResponseEntity<String> withdrawal(@ApiParam("withdrawal User")
                                             @RequestBody String id) throws Exception {
        userService.deleteById(id);
        return ResponseEntity.ok("회원탈퇴 완료");
    }


    /**
     * @deprecated  {@link #signin(token)}
     */
    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 403, message = "승인 거절"),
    })
    public ResponseEntity<UserDto> signup(@ApiParam("Signup User")
                                          @RequestBody String code) throws Exception {
        log.info(">> 소셜 회원가입 API client 받은 code :: {}", code);
        return ResponseEntity.ok(transToUserDto(userService.signup(code)));
    }

    private UserDto transToUserDto(User user) {
        user.setUserId(user.getId());
        user.setEmail(user.getEmail());
        return modelMapper.map(user, UserDto.class);
    }

}
