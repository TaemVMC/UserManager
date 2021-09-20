package com.verifymycoin.UserManager.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verifymycoin.UserManager.GoogleAuth.GoogleOauth;
import com.verifymycoin.UserManager.exception.BizException;
import com.verifymycoin.UserManager.exception.ResponseCodeMessage;
import com.verifymycoin.UserManager.user.domain.User;
import com.verifymycoin.UserManager.user.domain.UserDto;
import com.verifymycoin.UserManager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final GoogleOauth googleOauth;
    private final UserRepository userRepository;


    @Override
    public User signin(String code) throws Exception {
        User userForSignIn = User.transToUser(googleOauth.getUserInfo(code));
        exceptionHandingWithinSignIn(userForSignIn);
        return this.getBySub(userForSignIn.getSub());
    }

    @Override
    public User signup(String code) throws Exception {
//        check
        User userForSave = User.transToUser(googleOauth.getUserInfo(code));
        exceptionHandingWithinSignUp(userForSave);
        return this.save(userForSave);
    }

    private void exceptionHandingWithinSignIn(User user) {
        if (!existsBySub(user.getSub())) {
            throw ResponseCodeMessage.ERROR_0003.exception();
        }
    }

    private void exceptionHandingWithinSignUp(User user) {
        if (existsBySub(user.getSub())) {
            throw ResponseCodeMessage.ERROR_0002.exception();
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
//        return userRepository
//                .findAll()
//                .stream()
//                .map(user -> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public boolean existsBySub(String sub) {
        return userRepository.existsBySub(sub);
    }

    @Override
    public User getBySub(String sub) {
        return userRepository.getBySub(sub);
    }
    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User getById(String id) {
        return userRepository.getById(id);
    }



    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public boolean existById(String id) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }



    @Override
    public void deleteById(String id) {

    }

    @Override
    public void deleteAll() {

    }




}
