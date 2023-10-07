package com.socialmedia.commentingsystem.commentingservice.repository;

import com.socialmedia.commentingsystem.commentingservice.entity.Comment;
import com.socialmedia.commentingsystem.commentingservice.entity.Dislike;
import com.socialmedia.commentingsystem.commentingservice.entity.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentCustomRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CommentRepository commentRepository;

    public Comment save(Comment comment) {
        Comment commentEntity = commentRepository.save(comment);
        return commentEntity;
    }

    public Optional<Comment> findById(String commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> getReplies(String parentId) {
        Query query = new Query(Criteria.where("parent_comment_id").is(parentId));
        List<Comment> comments = mongoTemplate.find(query, Comment.class);
        if (comments != null) {
            return comments;
        } else {
            // Handle the case where the post is not found, e.g., return an empty list or throw an exception
            return Collections.emptyList();
        }
    }

    public List<Like> getLikes(String commentId) {
        Query query = new Query(Criteria.where("parent_id").is(commentId));
        List<Like> likes = mongoTemplate.find(query, Like.class);
        if (likes != null) {
            return likes;
        } else {
            // Handle the case where the post is not found, e.g., return an empty list or throw an exception
            return Collections.emptyList();
        }
    }

    public List<Dislike> getDislikes(String commentId) {
        Query query = new Query(Criteria.where("parent_id").is(commentId));
        List<Dislike> dislikes = mongoTemplate.find(query, Dislike.class);
        if (dislikes != null) {
            return dislikes;
        } else {
            // Handle the case where the post is not found, e.g., return an empty list or throw an exception
            return Collections.emptyList();
        }
    }
}
