package com.socialmedia.commentingsystem.commentingservice.service;

import com.socialmedia.commentingsystem.commentingservice.domain.CommentRequest;
import com.socialmedia.commentingsystem.commentingservice.domain.ReactionRequest;
import com.socialmedia.commentingsystem.commentingservice.entity.Comment;
import com.socialmedia.commentingsystem.commentingservice.entity.Dislike;
import com.socialmedia.commentingsystem.commentingservice.entity.Like;
import com.socialmedia.commentingsystem.commentingservice.repository.DislikeRepository;
import com.socialmedia.commentingsystem.commentingservice.repository.LikeRepository;
import com.socialmedia.commentingsystem.commentingservice.repository.PostCustomRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostCustomRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private DislikeRepository dislikeRepository;

    public boolean postComment(String postId, CommentRequest commentRequest) {
        try {
            Comment comment = Comment.builder().content(commentRequest.getContent()).userId(commentRequest.getUserId()).createdAt(new Date()).postId(postId).id(new ObjectId().toString()).build();
            // Delegate to the CommentRepository to save the comment
            Comment result = postRepository.saveComment(postId, comment);
            return result != null;
        } catch (Exception e) {
            // Handle exceptions (e.g., database errors)
            return false;
        }
    }

    public List<Comment> getFirstLevelComments(String postId, int page, int pageSize) {
        return postRepository.getFirstLevelComments(postId, page, pageSize);
    }

    public boolean likePost(String parentId, ReactionRequest likeRequest) {
        try {
            Like likeReaction = Like.builder().userId(likeRequest.getUserId()).parentId(parentId).id(new ObjectId().toString()).build();
            // Delegate to the CommentRepository to save the comment
            Like result = likeRepository.save(likeReaction);
            return result != null;
        } catch (Exception e) {
            // Handle exceptions (e.g., database errors)
            return false;
        }
    }

    public boolean dislikePost(String postId, ReactionRequest dislike) {
        try {
            Dislike dislikeReaction = Dislike.builder().userId(dislike.getUserId()).parentId(postId).id(new ObjectId().toString()).build();
            // Delegate to the CommentRepository to save the comment
            Dislike result = dislikeRepository.save(dislikeReaction);
            return result != null;
        } catch (Exception e) {
            // Handle exceptions (e.g., database errors)
            return false;
        }
    }

    public List<Like> getLikes(String postId, int page, int pageSize) {
        return postRepository.getLikes(postId, page, pageSize);
    }

    public List<Dislike> getDislikes(String postId, int page, int pageSize) {
        return postRepository.getDislikes(postId, page, pageSize);
    }
}
