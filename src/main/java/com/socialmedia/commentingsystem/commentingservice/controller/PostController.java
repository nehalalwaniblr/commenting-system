package com.socialmedia.commentingsystem.commentingservice.controller;

import com.socialmedia.commentingsystem.commentingservice.domain.CommentRequest;
import com.socialmedia.commentingsystem.commentingservice.domain.ReactionRequest;
import com.socialmedia.commentingsystem.commentingservice.entity.Comment;
import com.socialmedia.commentingsystem.commentingservice.entity.Dislike;
import com.socialmedia.commentingsystem.commentingservice.entity.Like;
import com.socialmedia.commentingsystem.commentingservice.service.PostService;
import com.socialmedia.commentingsystem.commentingservice.utils.LogGate;

import static com.socialmedia.commentingsystem.commentingservice.utils.LogUtils.myLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/{postId}/comment")
    public ResponseEntity<String> postComment(@PathVariable String postId, @RequestBody CommentRequest commentRequest) {
        boolean success = postService.postComment(postId, commentRequest);
        if (success) {
            return ResponseEntity.ok("Comment added successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add the comment.");
        }
    }

    //    Get first level comments for the given post Id
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable String postId, @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        List<Comment> comments = postService.getFirstLevelComments(postId, page, pageSize);
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return a 404 status if no comments are found
        } else {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
    }

    //  Add likes to a post
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable String postId, @RequestBody ReactionRequest likeRequest) {
        boolean success = postService.likePost(postId, likeRequest);
        if (success) {
            return ResponseEntity.ok("Comment added successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add the comment.");
        }
    }

    //  Add dis-likes to a post
    @PostMapping("/{postId}/dislike")
    public ResponseEntity<Void> dislikePost(@PathVariable String postId, @RequestBody ReactionRequest dislike) {
        postService.dislikePost(postId, dislike);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/likes")
    public ResponseEntity<List<Like>> getLikes(@PathVariable String postId, @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int pageSize) {
        myLog("PostController.getLikes", LogGate.ENTRY, "getLikes() started for postId : {} page : {} pageSize: {}", postId, page, pageSize);
        List<Like> likes = postService.getLikes(postId, page, pageSize);
        if (likes.isEmpty()) {
            myLog("PostController.getLikes", LogGate.EXIT, "No records found", postId, page, pageSize);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return a 404 status if no comments are found
        } else {
            myLog("PostController.getLikes", LogGate.EXIT, "getLikes()--end", postId, page, pageSize);
            return new ResponseEntity<>(likes, HttpStatus.OK);
        }

    }

    @GetMapping("/{postId}/dislikes")
    public ResponseEntity<List<Dislike>> getDislikes(@PathVariable String postId, @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        List<Dislike> dislikes = postService.getDislikes(postId, page, pageSize);
        if (dislikes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return a 404 status if no comments are found
        } else {
            return new ResponseEntity<>(dislikes, HttpStatus.OK);
        }
    }
}
