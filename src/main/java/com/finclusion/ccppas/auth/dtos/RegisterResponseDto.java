package com.finclusion.ccppas.auth.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {

    private String email;
    @JsonProperty("fmoj_id")
    private String uniqueId;
    private String firstname;
    private String lastname;
}
