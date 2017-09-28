package com.vjy.justfollow.validation;


import com.vjy.justfollow.common.mongodb.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceValidation {

    @Autowired
    private UsersRepository usersRepository ;

    /**
     *Method for check UserId is already exists
     * @param mailId
     * @return
     */
    public boolean isUserAlreadyExists(String mailId){
        return usersRepository.findByMailId(mailId) != null;
    }





    public boolean isFbIdUserExists(String fbId) {
        return usersRepository.findByFbId(fbId) != null;
    }
}
