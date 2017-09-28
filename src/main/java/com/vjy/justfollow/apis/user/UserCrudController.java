package com.vjy.justfollow.apis.user;


import com.vjy.justfollow.common.response.CommonResponse;
import com.vjy.justfollow.common.response.IOResponse;
import com.vjy.justfollow.common.utils.JustFollowUtils;
import com.vjy.justfollow.exceptionHandling.ErrorMessage;
import com.vjy.justfollow.services.facebook.FacebookService;
import com.vjy.justfollow.services.user.UserCrudService;
import com.vjy.justfollow.validation.UserServiceValidation;
import com.vjy.justfollow.vo.user.UserCreateVO;
import com.vjy.justfollow.vo.user.UserFbLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserCrudController {

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private UserServiceValidation serviceValidation;

    @Autowired
    private JustFollowUtils justFollowUtils;

    @Autowired
    private FacebookService facebookService;


    /**
     * user registration via email
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestParam("file")MultipartFile file,
                                    @RequestParam String name,
                                    @RequestParam String mailId,
                                    @RequestParam(required = false) String mobileNumber,
                                    @RequestParam(required = false) String password,
                                    @RequestParam(required = false) String dob,
                                    @RequestParam(required = false) String userId) {


        if (serviceValidation.isUserAlreadyExists(mailId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Mail Id already exists"));
        }

        IOResponse ioResponse = justFollowUtils
                .saveFile(file, UUID.randomUUID().toString()+System.currentTimeMillis());

        if (ioResponse.getStatus() == IOResponse.Status.SUCCESS) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userCrudService.createUser(new UserCreateVO(name, mailId, mobileNumber, password, userId, ioResponse.getData().toString(),dob)));
        }else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Check imageFile"));
        }
    }



    /**
     * user ragistration via facebook login
     */
    @RequestMapping(value = "/registerWithFacebook", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createViaFb(@RequestParam("file")MultipartFile file,
                                         @RequestParam String name,
                                         @RequestParam String mailId,
                                         @RequestParam(required = false) String mobileNumber,
                                         @RequestParam(required = false) String password,
                                         @RequestParam(required = false) String userId,
                                         @RequestParam(required = false) String dob,
                                         @RequestParam String fbId) {

        if (serviceValidation.isUserAlreadyExists(mailId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Mail Id already exists"));
        }


        IOResponse ioResponse = justFollowUtils.
                saveFile(file, UUID.randomUUID().toString()+System.currentTimeMillis());


        if (ioResponse.getStatus() == IOResponse.Status.SUCCESS) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userCrudService.createUser(new UserCreateVO(name, mailId, mobileNumber, password, userId, fbId, ioResponse.getData().toString(), dob)));
        }else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Check imageFile"));
        }

    }



    @RequestMapping(value = "/loginWithFacebook", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> loginWithFacebook(@RequestBody UserFbLoginVo userFbLoginVo) {

        if (!serviceValidation.isFbIdUserExists(userFbLoginVo.getFbId())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new CommonResponse("User does not exist", 1, userFbLoginVo.getFbId()));
        }

        userFbLoginVo = facebookService.getFbDetails(userFbLoginVo.getFbId(), userFbLoginVo.getFbToken());

        if (userFbLoginVo.getStatusCode() == 200) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CommonResponse(null, 0, userCrudService.getUserDetailFromFbId(userFbLoginVo.getFbId())));

        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new CommonResponse("Wrong facebook information", 1, userFbLoginVo.getFbId()));
        }
    }



    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileByFbToken (@RequestParam String fbAccessToken) {
        UserFbLoginVo fbLoginVo = facebookService.getFbDetails(fbAccessToken);

        if (fbLoginVo.getStatusCode() == 200 && serviceValidation.isFbIdUserExists(fbLoginVo.getFbId())) {
            return ResponseEntity.status(HttpStatus.OK).body(new CommonResponse(null, 0, userCrudService.getUserDetailFromFbId(fbLoginVo.getFbId())));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CommonResponse("Wrong facebook information", 1, fbAccessToken));
        }
    }



/*
    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    public UserCreateVO fimdUser() {


        return userCrudService.getUserDetail("59c8f33215282d210493b286");
    }*/


    /**
     *return file according to file name
     */
    @RequestMapping(value = "img/{fileName}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getFile(@PathVariable("fileName") String fileName){

        return justFollowUtils.getFile(fileName);

    }
}
