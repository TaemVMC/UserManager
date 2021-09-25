package com.verifymycoin.UserManager.user.service;

import com.verifymycoin.UserManager.GoogleAuth.GoogleOauth;
import com.verifymycoin.UserManager.common.exception.custom.UserNotExist;

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
    public User signin(String googleToken, String idToken){
        User userForSignIn = User.transToUser(googleOauth.getUserInfoByGoogleApi(googleToken, idToken));
        Optional<User> userInRepository = userRepository.findBySub(userForSignIn.getSub());

        // TODO : Optional한 로직으로 수정필요
        if(userInRepository.isPresent()){
            return userInRepository.get();
        }
        return userInRepository.orElse(this.save(userForSignIn));
    }

//    @Override
//    public User signin(String googleToken, String idToken) throws Exception {
//        User userForSignIn = User.transToUser(googleOauth.getUserInfoByGoogleApi(googleToken, idToken));
////        exceptionHandingWithinSignIn(userForSignIn);
//
//        return this.findBySub(userForSignIn.getSub()).orElse(this.save(userForSignIn));
////        TODO : add save function
////        return this.findBySub(userForSignIn.getSub()).get();
//    }

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
    public Optional<User> findByUserId(String userId) {
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotExist();
        }
        return userRepository.findById(userId);
    }

//    deprecated
    @Override
    public User getByUserId(String userId) {
        return userRepository.getByUserId(userId);
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }


    @Override
    public void deleteByUserId(String userId) {
        if(!userRepository.findById(userId).isPresent()){
            throw new UserNotExist();
        }
        userRepository.deleteById(userId);
    }


}
