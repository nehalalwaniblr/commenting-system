package com.socialmedia.commentingsystem.commentingservice.controller;

import com.socialmedia.commentingsystem.commentingservice.domain.CommentRequest;
import com.socialmedia.commentingsystem.commentingservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<String> postComment(@PathVariable String postId, @RequestBody CommentRequest commentRequest) {
        boolean success = commentService.postComment(postId, commentRequest);
        if (success) {
            return ResponseEntity.ok("Comment added successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add the comment.");
        }
    }

    //    Get first level comments for the given post Id
    @GetMapping("/{postId}")
    public ResponseEntity<Void> getComments(@PathVariable String postId) {
        commentService.getFirstLevelComments(postId);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }
//Add a reply to a comment.
//Add Like/Dislike:
}
