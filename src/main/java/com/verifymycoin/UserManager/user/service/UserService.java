package com.verifymycoin.UserManager.user.service;

import com.verifymycoin.UserManager.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    List<User> findAll();
    Optional<User> findByUserId(String userId);
    Optional<User> findByUsername(String username);

    User save(User entity);
    void deleteByUserId(String userId);

    User getByUserId(String userId);      //없을 수가 없는 값에는 optional 걸지 않음 (있는게 확실할 때 )
    Optional<User> getBySub(String sub);      //없을 수가 없는 값에는 optional 걸지 않음 (있는게 확실할 때 )
    boolean existsBySub(String sub);

    User signin(String googleToken, String idToken) throws Exception;
    User signup(String code) throws Exception;

    Optional<User> findBySub(String sub);


}
