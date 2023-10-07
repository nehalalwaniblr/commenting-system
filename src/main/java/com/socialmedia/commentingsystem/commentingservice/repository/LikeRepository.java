package com.socialmedia.commentingsystem.commentingservice.repository;

import com.socialmedia.commentingsystem.commentingservice.entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends MongoRepository<Like,String> {
}
