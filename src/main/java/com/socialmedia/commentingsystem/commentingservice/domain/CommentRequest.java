package com.socialmedia.commentingsystem.commentingservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;


@JsonIgnoreProperties(
        ignoreUnknown = true
)
@Data
@AllArgsConstructor
public class CommentRequest {
    @JsonProperty("userId")
    private final @NotNull(
            message = "user id cannot be blank"
    )
    String userId;

    @JsonProperty("postId")
    private final @NotNull(
            message = "postId id cannot be blank"
    )
    String postId;

//    @JsonProperty("Ho")
//    private final @NotNull(
//            message = "parentCommentId id cannot be blank"
//    )
//    String parentCommentId;

    @JsonProperty("content")
    private final @NotNull(
            message = "content cannot be blank"
    )
    String content;

}
