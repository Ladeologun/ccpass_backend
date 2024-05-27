package com.finclusion.ccppas.auth.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterResponseDto {

    private String email;
    @JsonProperty("fmoj_id")
    private String uniqueId;
    private String firstname;
    private String lastname;
}
