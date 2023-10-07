package com.socialmedia.commentingsystem.commentingservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection="posts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Post {
    @Id
    private String id;
    private String content;
    @Field("user_id")
    private String userId;
    @Field("created_at")
    private Date createdAt;
    @Field("updated_at")
    private Date updatedAt;
    @Field("comments")
    private List<Comment> comments;
}
