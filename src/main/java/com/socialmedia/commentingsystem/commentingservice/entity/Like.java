package com.socialmedia.commentingsystem.commentingservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Like {
    @Id
    @Field("_id")
    private String id;
    @Field("user_id")
    private String userId;
    @Field("parent_id")
    private String parentId;
}