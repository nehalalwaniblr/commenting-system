package com.socialmedia.commentingsystem.commentingservice.controller;

import com.socialmedia.commentingsystem.commentingservice.domain.CommentRequest;
import com.socialmedia.commentingsystem.commentingservice.domain.ReactionRequest;
import com.socialmedia.commentingsystem.commentingservice.entity.Comment;
import com.socialmedia.commentingsystem.commentingservice.entity.Dislike;
import com.socialmedia.commentingsystem.commentingservice.entity.Like;
import com.socialmedia.commentingsystem.commentingservice.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@Slf4j
public class CommentController {
    @Autowired
    CommentService commentService;

    //    Add a reply to a comment.
    @PostMapping("/reply/{commentId}")
    public ResponseEntity<String> addReply(
            @PathVariable("commentId") String parentCommentId,
            @RequestBody CommentRequest commentRequest) {

        Boolean savedReply = commentService.addComment(parentCommentId, commentRequest);
        if (savedReply != null) {
            return ResponseEntity.ok("Comment added successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add the reply");
        }
    }

    @GetMapping("/reply/{commentId}")
    public ResponseEntity<List<Comment>> getReplies(
            @PathVariable("commentId") String parentId) {

        List<Comment> comments = commentService.getReplies(parentId);
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return a 404 status if no comments are found
        } else {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
    }

    @PostMapping("/{commentId}/like")
    public ResponseEntity<String> likePost(@PathVariable String commentId, @RequestBody ReactionRequest likeRequest) {
        boolean success = commentService.likePost(commentId, likeRequest);
        if (success) {
            return ResponseEntity.ok("Comment added successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add the comment.");
        }
    }

    //  Add dis-likes to a post
    @PostMapping("/{commentId}/dislike")
    public ResponseEntity<Void> dislikePost(@PathVariable String commentId, @RequestBody ReactionRequest dislike) {
        commentService.dislikePost(commentId, dislike);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/likes")
    public ResponseEntity<List<Like>> getLikes(@PathVariable String commentId) {
        List<Like> likes = commentService.getLikes(commentId);
        if (likes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return a 404 status if no comments are found
        } else {
            return new ResponseEntity<>(likes, HttpStatus.OK);
        }
    }

    @GetMapping("/{postId}/dislikes")
    public ResponseEntity<List<Dislike>> getDislikes(@PathVariable String commentId) {
        List<Dislike> dislikes = commentService.getDislikes(commentId);
        if (dislikes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return a 404 status if no comments are found
        } else {
            return new ResponseEntity<>(dislikes, HttpStatus.OK);
        }
    }
}
