package com.example.somoim.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ResultError {
    @JsonIgnore
    private HttpStatus httpStatus;
    @JsonProperty(required = true)
    private String resultCode;
    @JsonProperty(required = true)
    private String resultMessage;
}