package com.socialmedia.commentingsystem.commentingservice.repository;

import com.socialmedia.commentingsystem.commentingservice.domain.CommentRequest;
import com.socialmedia.commentingsystem.commentingservice.entity.Comment;
import com.socialmedia.commentingsystem.commentingservice.entity.Post;
import com.socialmedia.commentingsystem.commentingservice.exceptions.PostNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public class CommentRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PostRepository postRepository;

    public void saveComment(String postId, CommentRequest commentRequest) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Comment comment = Comment.builder().content(commentRequest.getContent()).userId(commentRequest.getUserId()).createdAt(new Date()).postId(postId).build();
//            Comment savedComment = mongoTemplate.save(comment);
            Update update = new Update().addToSet("comments", comment);
            Query query = new Query(Criteria.where("_id").is(new ObjectId(postId)));
            mongoTemplate.updateFirst(query, update, Post.class);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }
}
