package com.socialmedia.commentingsystem.commentingservice.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dislike {
    private String dislikeId;
    private String userId;
}