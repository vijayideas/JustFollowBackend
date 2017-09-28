package com.vjy.justfollow.services.user;

import com.vjy.justfollow.common.response.CommonResponse;
import com.vjy.justfollow.vo.user.ChangePasswordVO;
import com.vjy.justfollow.vo.user.UserCreateVO;
import com.vjy.justfollow.vo.user.UserDetailVo;

public interface UserCrudService {

    public CommonResponse createUser(UserCreateVO userCreateVO );

    public CommonResponse verfiyUser(String userId);

    public CommonResponse updateUser(String userId , UserCreateVO userCreateVO);

    public CommonResponse forgotPassword(String userId);

    public CommonResponse changePassword(ChangePasswordVO changePasswordVO);

    //Update TO
    public CommonResponse updateUserType(String userId , String updateTo);

    public UserDetailVo getUserDetail(String objectId);

    public UserDetailVo getUserDetailFromFbId(String fbId);

    public String getUserIdFromFbId(String fbId);
}
