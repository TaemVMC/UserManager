package com.verifymycoin.UserManager;

//import com.verifymycoin.UserManager.GoogleAuth.GoogleOauth;
import com.verifymycoin.UserManager.user.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
public class HomeController {
    @Autowired
    UserController userController;

    @GetMapping("/")
    public String home(
            @RequestParam(name = "code") String code) throws Exception {
//        userController.findAllUser();
//        userController.signup(code);
//        userController.signin(code);

        return "";
    }


}
