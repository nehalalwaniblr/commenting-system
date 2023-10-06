package com.socialmedia.commentingsystem.commentingservice.controller;

import com.socialmedia.commentingsystem.commentingservice.domain.CommentRequest;
import com.socialmedia.commentingsystem.commentingservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    //  Add likes to a post
    @PostMapping("/{postId}/like")
    public ResponseEntity<Void> likePost(@PathVariable String postId) {
        postService.likePost(postId);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    //  Add dis-likes to a post
    @PostMapping("/{postId}/dislike")
    public ResponseEntity<Void> dislikePost(@PathVariable String postId) {
        postService.dislike(postId);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }
}
