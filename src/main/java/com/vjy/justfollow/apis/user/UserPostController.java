package com.vjy.justfollow.apis.user;


import com.vjy.justfollow.common.response.CommonResponse;
import com.vjy.justfollow.common.response.IOResponse;
import com.vjy.justfollow.common.utils.JustFollowUtils;
import com.vjy.justfollow.exceptionHandling.ErrorMessage;
import com.vjy.justfollow.services.facebook.FacebookService;
import com.vjy.justfollow.services.post.PostCrudService;
import com.vjy.justfollow.services.user.UserCrudService;
import com.vjy.justfollow.vo.post.PostCreateVO;
import com.vjy.justfollow.vo.user.UserFbLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/user/post")
public class UserPostController {

    @Autowired
    private UserCrudService userCrudService;
    @Autowired
    private PostCrudService postCrudService;

    @Autowired
    private FacebookService facebookService;

    @Autowired
    private JustFollowUtils justFollowUtils;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody PostCreateVO postCreateVO) {


        UserFbLoginVo fbLoginVo = facebookService.getFbDetails(postCreateVO.getFbAccessToken());

        if (fbLoginVo.getStatusCode() == 200 && userCrudService.getUserIdFromFbId(fbLoginVo.getFbId()).equals(postCreateVO.getUserId()) ) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(postCrudService.createPost(postCreateVO));
        }else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Please login again"));
        }
    }



    @RequestMapping(value = "/createWithMedia", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createMediaPost(@RequestParam("file")MultipartFile file,
                                             @RequestParam String userId,
                                             @RequestParam String fbAccessToken,
                                             @RequestParam String privacy,
                                             @RequestParam String mediaType,
                                             @RequestParam(required = false) String text) {


        UserFbLoginVo fbLoginVo = facebookService.getFbDetails(fbAccessToken);

        if (fbLoginVo.getStatusCode() == 200 && userCrudService.getUserIdFromFbId(fbLoginVo.getFbId()).equals(userId) ) {

            IOResponse ioResponse = justFollowUtils.
                    savePostFile(file, UUID.randomUUID().toString()+System.currentTimeMillis(), userId);

            if (ioResponse.getStatus() == IOResponse.Status.SUCCESS) {


                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(postCrudService.createPost(new PostCreateVO(userId, text, mediaType, ioResponse.getData().toString(), privacy)));
            }else {
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Error in file upload"));
            }


        }else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Please login again"));
        }
    }






    @RequestMapping(value = "/allPost", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAll(@RequestParam String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(postCrudService.getAllPost(userId));
    }



    /**
     *return file according to file name
     */
    @RequestMapping(value = "/deleteAll" , method = RequestMethod.GET)
    public ResponseEntity<Object> deleteAll(@RequestParam int pass){

        if (pass == 5394) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(postCrudService.deleteAll());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("wrong password",1, pass));

    }











    @RequestMapping(value = "img/{userId}/{fileName}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getPostFile(@PathVariable("userId") String userId,@PathVariable("fileName") String fileName){

        return justFollowUtils.getPostFile(userId, fileName);

    }



}
