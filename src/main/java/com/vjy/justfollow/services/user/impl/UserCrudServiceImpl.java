package com.vjy.justfollow.services.user.impl;


import com.vjy.justfollow.common.enums.UserCreateFrom;
import com.vjy.justfollow.common.enums.UserStatus;
import com.vjy.justfollow.common.mongodb.model.UserMongoObject;
import com.vjy.justfollow.common.mongodb.repository.UsersRepository;
import com.vjy.justfollow.common.response.CommonResponse;
import com.vjy.justfollow.common.utils.JustFollowUtils;
import com.vjy.justfollow.services.user.UserCrudService;
import com.vjy.justfollow.vo.user.ChangePasswordVO;
import com.vjy.justfollow.vo.user.UserCreateVO;
import com.vjy.justfollow.vo.user.UserDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserCrudServiceImpl implements UserCrudService {


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JustFollowUtils justFollowUtils;

//    private static final Random RANDOM = new SecureRandom();


    @Override
    public CommonResponse createUser(UserCreateVO userCreateVO) {

        Date dateOfBirth = null;
        try {
            dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(userCreateVO.getDateOfBirth());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        UserMongoObject users = new UserMongoObject(userCreateVO.getName(), userCreateVO.getMailId(),
                userCreateVO.getMobileNumber(), userCreateVO.getUserId(), userCreateVO.getPicFileName(), dateOfBirth, UserCreateFrom.FACEBOOK.toString(), UserStatus.NOT_VERIFIED.getStatus(),
                new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));

        if (userCreateVO.getFbId() != null) {
            users.setFbId(userCreateVO.getFbId());
        }

        usersRepository.save(users);

        return new CommonResponse("User created successfully", 1, users.getId());
    }

    @Override
    public CommonResponse verfiyUser(String userId) {
        return null;
    }

    @Override
    public CommonResponse updateUser(String userId, UserCreateVO userCreateVO) {
        return null;
    }

    @Override
    public CommonResponse forgotPassword(String userId) {
        return null;
    }

    @Override
    public CommonResponse changePassword(ChangePasswordVO changePasswordVO) {
        return null;
    }

    @Override
    public CommonResponse updateUserType(String userId, String updateTo) {
        return null;
    }

    @Override
    public UserDetailVo getUserDetail(String objectId) {

        UserMongoObject user = usersRepository.findOne(objectId);
        UserDetailVo createVO = new UserDetailVo();
        createVO.setName(user.getName());
        createVO.setMailId(user.getMailId());
        createVO.setMobileNumber(user.getMobileNumber());

        createVO.setUserId(user.getUserId());

        createVO.setPicUrl(user.getPicFileName());


        return createVO;
    }


    @Override
    public UserDetailVo getUserDetailFromFbId(String fbId) {

        UserMongoObject user = usersRepository.findByFbId(fbId);

        return new UserDetailVo(user.getId(), user.getName(), user.getMailId(), user.getMobileNumber(), user.getUserId(), user.getFbId(), user.getPicFileName(), user.getDateOfBirth());
    }

    @Override
    public String getUserIdFromFbId(String fbId) {
        UserMongoObject user = usersRepository.findByFbId(fbId);
        return user.getId();
    }
}
