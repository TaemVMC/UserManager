package com.verifymycoin.UserManager.user.service;

import com.verifymycoin.UserManager.GoogleAuth.GoogleOauth;
import com.verifymycoin.UserManager.exception.ResponseCodeMessage;
import com.verifymycoin.UserManager.user.domain.User;
import com.verifymycoin.UserManager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final GoogleOauth googleOauth;
    private final UserRepository userRepository;


    @Override
    public User signin(String googleToken, String idToken) throws Exception {
        User userForSignIn = User.transToUser(googleOauth.getUserInfoByGoogleApi(googleToken, idToken));
//        exceptionHandingWithinSignIn(userForSignIn);

//        return this.findBySub(userForSignIn.getSub()).orElse(this.save(userForSignIn));
//        TODO : add save function
        return this.findBySub(userForSignIn.getSub()).get();
    }

    @Override
    public User signup(String code) throws Exception {
//        check
//        User userForSave = User.transToUser(googleOauth.getUserInfoByGoogleApi(code));
//        exceptionHandingWithinSignUp(userForSave);
//        return this.save(userForSave);
        return null;
    }

    @Override
    public Optional<User> findBySub(String sub) {
        return userRepository.findBySub(sub);
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
        System.out.println("save1!!!!! user!!! wjy??      " + entity.getSub() + "   " + entity.getEmail());
        return userRepository.save(entity);
    }

    @Override
    public boolean existsBySub(String sub) {
        return userRepository.existsBySub(sub);
    }

    @Override
    public Optional<User> getBySub(String sub) {
        return userRepository.getBySub(sub);
    }
    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

//    deprecated
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
        userRepository.deleteById(id);

    }

    @Override
    public void deleteAll() {

    }




}
