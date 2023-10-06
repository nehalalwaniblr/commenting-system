package com.socialmedia.commentingsystem.commentingservice.service;

import com.socialmedia.commentingsystem.commentingservice.domain.CommentRequest;
import com.socialmedia.commentingsystem.commentingservice.entity.Comment;
import com.socialmedia.commentingsystem.commentingservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public boolean postComment(String postId, CommentRequest commentRequest) {
        try {
            // Delegate to the CommentRepository to save the comment
            commentRepository.saveComment(postId, commentRequest);
            return true;
        } catch (Exception e) {
            // Handle exceptions (e.g., database errors)
            return false;
        }
    }

    public void getFirstLevelComments(String postId) {
    }

}
