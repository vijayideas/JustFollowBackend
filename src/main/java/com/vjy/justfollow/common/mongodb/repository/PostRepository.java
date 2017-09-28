package com.vjy.justfollow.common.mongodb.repository;

import com.vjy.justfollow.common.mongodb.model.PostMongoObject;
import com.vjy.justfollow.vo.post.PostDetailsVo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<PostMongoObject, String> {

    List<PostMongoObject> findAllByOrderByCreatedDateDesc();
}
