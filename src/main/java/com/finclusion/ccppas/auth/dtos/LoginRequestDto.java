package com.finclusion.ccppas.auth.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequestDto {

    @NotEmpty(message = "password cannot be empty")
    @NotBlank(message = "password cannot be blank")
    @Size(min = 8, message = "password should be more than 8 characters")
    private String password;

    @NotEmpty(message = "id cannot be empty")
    @NotBlank(message = "id cannot be blank")
    @JsonProperty("fmoj_id")
    private String uniqueId;
}
