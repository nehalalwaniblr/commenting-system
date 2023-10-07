package com.socialmedia.commentingsystem.commentingservice.repository;

import com.socialmedia.commentingsystem.commentingservice.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment,String> {
}
