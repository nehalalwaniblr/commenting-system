package com.socialmedia.commentingsystem.commentingservice.service;

import com.socialmedia.commentingsystem.commentingservice.repository.DislikeRepository;
import com.socialmedia.commentingsystem.commentingservice.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DislikeService {
    @Autowired
    DislikeRepository dislikeRepository;
}
