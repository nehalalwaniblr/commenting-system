package com.socialmedia.commentingsystem.commentingservice.repository;

import com.mongodb.client.result.UpdateResult;
import com.socialmedia.commentingsystem.commentingservice.entity.Comment;
import com.socialmedia.commentingsystem.commentingservice.entity.Dislike;
import com.socialmedia.commentingsystem.commentingservice.entity.Like;
import com.socialmedia.commentingsystem.commentingservice.entity.Post;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class PostCustomRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public Comment saveComment(String postId, Comment comment) {
        Update update = new Update().addToSet("comments", comment);
        Query query = new Query(Criteria.where("_id").is(new ObjectId(postId)));
        UpdateResult result = mongoTemplate.updateFirst(query, update, Post.class);
        if (result.getModifiedCount() == 1)
            return comment;
        else return null;
    }

    public List<Comment> getFirstLevelComments(String postId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Query query = new Query(Criteria.where("_id").is(postId)).with(pageable);
        Post post = mongoTemplate.findOne(query, Post.class);
        if (post != null) {
            return post.getComments();
        } else {
            // Handle the case where the post is not found, e.g., return an empty list or throw an exception
            return Collections.emptyList();
        }
    }

    public List<Like> getLikes(String postId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Query query = new Query(Criteria.where("parent_id").is(postId)).with(pageable);
        List<Like> likes = mongoTemplate.find(query, Like.class);
        if (likes != null) {
            return likes;
        } else {
            // Handle the case where the post is not found, e.g., return an empty list or throw an exception
            return Collections.emptyList();
        }
    }

    public List<Dislike> getDislikes(String postId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Query query = new Query(Criteria.where("parent_id").is(postId)).with(pageable);

        List<Dislike> dislikes = mongoTemplate.find(query, Dislike.class);
        if (dislikes != null) {
            return dislikes;
        } else {
            // Handle the case where the post is not found, e.g., return an empty list or throw an exception
            return Collections.emptyList();
        }
    }
}
