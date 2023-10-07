package com.socialmedia.commentingsystem.commentingservice.repository;

import com.socialmedia.commentingsystem.commentingservice.entity.Dislike;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DislikeRepository extends MongoRepository<Dislike,String> {
}
