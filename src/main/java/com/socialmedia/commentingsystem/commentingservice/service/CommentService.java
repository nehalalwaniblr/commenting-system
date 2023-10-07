package com.socialmedia.commentingsystem.commentingservice.service;

import com.socialmedia.commentingsystem.commentingservice.domain.CommentRequest;
import com.socialmedia.commentingsystem.commentingservice.domain.ReactionRequest;
import com.socialmedia.commentingsystem.commentingservice.entity.Comment;
import com.socialmedia.commentingsystem.commentingservice.entity.Dislike;
import com.socialmedia.commentingsystem.commentingservice.entity.Like;
import com.socialmedia.commentingsystem.commentingservice.repository.CommentCustomRepository;
import com.socialmedia.commentingsystem.commentingservice.repository.DislikeRepository;
import com.socialmedia.commentingsystem.commentingservice.repository.LikeRepository;
import com.socialmedia.commentingsystem.commentingservice.repository.PostRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentCustomRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    DislikeRepository dislikeRepository;

    public boolean addComment(String parentCommentId, CommentRequest commentRequest) {
        if (parentCommentId != null) {
            Comment comment = Comment.builder().content(commentRequest.getContent()).userId(commentRequest.getUserId()).createdAt(new Date()).postId(commentRequest.getPostId()).id(new ObjectId().toString()).parentCommentId(parentCommentId).build();
            return commentRepository.save(comment)!=null;
        }
        return false;
    }

    public List<Comment> getReplies(String parentId) {
        if(parentId!=null){
            return commentRepository.getReplies(parentId);
        }
        return null;
    }

    public boolean likePost(String parentId, ReactionRequest likeRequest)  {
        try{
            Like likeReaction = Like.builder().userId(likeRequest.getUserId()).parentId(parentId).id(new ObjectId().toString()).build();
            // Delegate to the CommentRepository to save the comment
            Like result = likeRepository.save(likeReaction);
            return result!=null;
        } catch (Exception e) {
            // Handle exceptions (e.g., database errors)
            return false;
        }
    }

    public boolean dislikePost(String postId, ReactionRequest dislike) {
        try{
            Dislike dislikeReaction = Dislike.builder().userId(dislike.getUserId()).parentId(postId).id(new ObjectId().toString()).build();
            // Delegate to the CommentRepository to save the comment
            Dislike result = dislikeRepository.save(dislikeReaction);
            return result!=null;
        } catch (Exception e) {
            // Handle exceptions (e.g., database errors)
            return false;
        }
    }

    public List<Like> getLikes(String commentId) {
        return commentRepository.getLikes(commentId);
    }

    public List<Dislike> getDislikes(String commentId) {
        return commentRepository.getDislikes(commentId);
    }
}
