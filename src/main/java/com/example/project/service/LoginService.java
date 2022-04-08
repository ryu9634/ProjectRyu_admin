package com.example.project.service;

import com.example.project.model.entity.User;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    @Autowired
    private final UserRepository userRepository;

    public boolean login(User user){
        User findUser = userRepository.findByUserUserid(user.getUserUserid());

        if(findUser == null){
            return false;
        }

        if(!findUser.getUserUserpw().equals(user.getUserUserpw())){
            return false;
        }
        return true;
    }
}
