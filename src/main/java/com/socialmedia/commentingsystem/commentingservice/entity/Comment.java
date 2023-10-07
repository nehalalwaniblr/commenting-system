package com.socialmedia.commentingsystem.commentingservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection="comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @Field("_id")
    private String id;
    @Field("user_id")
    private String userId;
    @Field("post_id")
    private String postId;
    @Field("parent_comment_id")
    private String parentCommentId;
    private String content;
    @Field("created_at")
    private Date createdAt;
    @Field("updated_at")
    private Date updatedAt;
}
