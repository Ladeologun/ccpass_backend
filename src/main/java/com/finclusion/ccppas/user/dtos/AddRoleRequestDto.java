package com.finclusion.ccppas.user.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddRoleRequestDto {

    @NotEmpty(message = "name cannot be empty")
    @NotBlank(message = "name cannot be blank")
    @JsonProperty("fmoj_id")
    private String name;
}
