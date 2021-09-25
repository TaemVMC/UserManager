package com.verifymycoin.UserManager.user.controller;


import com.verifymycoin.UserManager.common.response.AppCode;
import com.verifymycoin.UserManager.common.response.ResponseWrapper;
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
import java.util.Optional;
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

    @GetMapping("/{userId}")
    @ApiOperation(value = "${UserController.userInfo}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 403, message = "승인 거절"),
    })
    public ResponseEntity<ResponseWrapper> getUserInfo(@PathVariable String userId) throws Exception {
        Optional<User> user = userService.findByUserId(userId);
        ResponseWrapper response = new ResponseWrapper(AppCode.SUCCESS, transToUserDto(user.get()));
        return ResponseEntity.ok(response);
    }


    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "구글 Oauth 에러")
    })
    public ResponseEntity<ResponseWrapper> signin(@ApiParam("Signup User")
                                         @RequestHeader String Authorization, @RequestBody String idToken) throws Exception {
        User user = userService.signin(Authorization, idToken);
        System.out.println("user = " + user);
        ResponseWrapper response = new ResponseWrapper(AppCode.SUCCESS, transToUserDto(user));
        return ResponseEntity.ok(response);
    }


//    TODO : status로 변경
    @PostMapping("/withdrawal")
    @ApiOperation(value = "${UserController.withdrawal}")
    public ResponseEntity<ResponseWrapper> withdrawal(@ApiParam("withdrawal User")
                                                          @RequestHeader String userId){
        userService.deleteByUserId(userId);
        ResponseWrapper response = new ResponseWrapper(AppCode.SUCCESS);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/allUserInfo")
    public ResponseEntity<ResponseWrapper> findAllUser() {

        log.info("getAllUserInfo API");
//        log.info("request header value : {}", header);
//        return ResponseEntity.ok(
//                userService
//                        .findAll()
//                        .stream().map(this::transToUserDto)
//                        .collect(Collectors.toList()));
//        TODO : USER -> USERDto change
        List<User> users = userService.findAll().stream().collect(Collectors.toList());
        ResponseWrapper response = new ResponseWrapper(AppCode.SUCCESS, users);
        return ResponseEntity.ok(response);
    }

    private UserDto transToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
