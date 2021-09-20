package com.verifymycoin.UserManager.user.service;

import com.verifymycoin.UserManager.user.domain.User;
import com.verifymycoin.UserManager.user.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    List<User> findAll();
    Optional<User> findById(String id);
    Optional<User> findByUsername(String username);
    boolean existById(String id);
    int count();
    User save(User entity);
    void deleteById(String id);
    void deleteAll();
    User getById(String id);      //없을 수가 없는 값에는 optional 걸지 않음 (있는게 확실할 때 )
    User getBySub(String sub);      //없을 수가 없는 값에는 optional 걸지 않음 (있는게 확실할 때 )
    boolean existsBySub(String sub);
    User signin(String code) throws Exception;
    User signup(String code) throws Exception;

}
