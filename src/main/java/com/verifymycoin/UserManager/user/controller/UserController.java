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
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Api(tags = "users")
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 403, message = "승인 거절"),
    })
    public ResponseEntity<UserDto> signup(@ApiParam("Signup User")
                                         @RequestBody String code) throws Exception {
        log.info(">> 소셜 회원가입 API client 받은 code :: {}", code);
        return ResponseEntity.ok(modelMapper.map(userService.signup(code), UserDto.class));
    }

    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 403, message = "승인 거절"),
    })
    public ResponseEntity<UserDto> signin(@ApiParam("Signup User")
                                         @RequestBody String code) throws Exception {
        log.info(">> 소셜 로그인 API client 받은 code :: {}", code);

        return ResponseEntity.ok(new UserDto("1234", "test", "/url", "test given name" , "test family name"));
//        return ResponseEntity.ok(modelMapper.map(userService.signin(code), UserDto.class));
    }

   @PostMapping("/withdrawal")
    @ApiOperation(value = "${UserController.withdrawal}")
    public ResponseEntity<String> withdrawal(@ApiParam("withdrawal User")
                                         @RequestBody String id) throws Exception {
        userService.deleteById(id);
        return ResponseEntity.ok("회원탈퇴 완료");
    }

   @GetMapping("/userInfo/{id}")
    @ApiOperation(value = "${UserController.userInfo}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 403, message = "승인 거절"),
    })
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String id) throws Exception {

        return ResponseEntity.ok(modelMapper.map(userService.getById(id), UserDto.class));
    }


    @GetMapping("/allUserInfo")
    public ResponseEntity<List<UserDto>> findAllUser() {
        log.info("getAllUserInfo API");
//        log.info("request header value : {}", header);
        return ResponseEntity.ok(
                userService
                        .findAll()
                        .stream().map(user -> {
                            user.setUserId(user.getId());
                            return modelMapper.map(user, UserDto.class);
                        })
                        .collect(Collectors.toList()));
    }
}
