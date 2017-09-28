package com.vjy.justfollow.common.mongodb.repository;

import com.vjy.justfollow.common.mongodb.model.UserMongoObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<UserMongoObject, String> {

    public UserMongoObject findByUserIdAndStatus(String userId,int status);

    public UserMongoObject findByUserId(String userId);
    public UserMongoObject findByMailId(String mailId);

    public UserMongoObject findByFbId(String fbId);

}
