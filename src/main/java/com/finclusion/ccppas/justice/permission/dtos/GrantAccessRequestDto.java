package com.finclusion.ccppas.justice.permission.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrantAccessRequestDto {

    @NotNull(message = "id cannot be null")
    @Digits(message = "id must be a digit", integer = Integer.MAX_VALUE, fraction = 0)
    private Long id;
}
