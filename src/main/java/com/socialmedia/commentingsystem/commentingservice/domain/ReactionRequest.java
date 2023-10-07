package com.socialmedia.commentingsystem.commentingservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ReactionRequest {
    @JsonProperty("userId")
    private final @NotNull(
            message = "user id cannot be blank"
    )
    String userId;

}
