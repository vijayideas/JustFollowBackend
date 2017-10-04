package com.vjy.justfollow.apis.common;

import com.vjy.justfollow.common.response.CommonResponse;
import com.vjy.justfollow.common.utils.JustFollowUtils;
import com.vjy.justfollow.services.common.PostService;
import com.vjy.justfollow.services.facebook.FacebookService;
import com.vjy.justfollow.services.post.PostCrudService;
import com.vjy.justfollow.services.user.UserCrudService;
import com.vjy.justfollow.validation.UserServiceValidation;
import com.vjy.justfollow.vo.common.PostLikeDislikeVO;
import com.vjy.justfollow.vo.user.UserFbLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserCrudService userCrudService;
    @Autowired
    private PostCrudService postCrudService;

    @Autowired
    private FacebookService facebookService;

    @Autowired
    private JustFollowUtils justFollowUtils;

    @Autowired
    private UserServiceValidation serviceValidation;


    @RequestMapping(value = "/allPost", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAll(@RequestParam String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPost(userId));
    }


    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public ResponseEntity<?> doPostLike(@RequestBody PostLikeDislikeVO likeDislikeVO) {
        UserFbLoginVo fbLoginVo = facebookService.getFbDetails(likeDislikeVO.getFbAccessToken());

        if (fbLoginVo.getStatusCode() == 200 && serviceValidation.isFbIdUserExists(fbLoginVo.getFbId())) {
            return ResponseEntity.status(HttpStatus.OK).body(postService.doLike(likeDislikeVO.getPostId(), userCrudService.getUserIdFromFbId(fbLoginVo.getFbId())));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Wrong information", 1, likeDislikeVO));
        }
    }



    @RequestMapping(value = "/disLike", method = RequestMethod.POST)
    public ResponseEntity<?> doPostDisLike(@RequestBody PostLikeDislikeVO likeDislikeVO) {
        UserFbLoginVo fbLoginVo = facebookService.getFbDetails(likeDislikeVO.getFbAccessToken());

        if (fbLoginVo.getStatusCode() == 200 && serviceValidation.isFbIdUserExists(fbLoginVo.getFbId())) {
            return ResponseEntity.status(HttpStatus.OK).body(postService.doDislike(likeDislikeVO.getPostId(), userCrudService.getUserIdFromFbId(fbLoginVo.getFbId())));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Wrong information", 1, likeDislikeVO));
        }
    }



}
