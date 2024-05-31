package com.finclusion.ccppas.justice.permission.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finclusion.ccppas.auth.validations.GovernmentEmail;
import com.finclusion.ccppas.auth.validations.IsValidJustice;
import com.finclusion.ccppas.auth.validations.IsValidJusticeFmoj;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccessDto {

    @NotEmpty(message = "id cannot be empty")
    @NotBlank(message = "id cannot be empty")
    @JsonProperty("fmoj_id")
    @IsValidJusticeFmoj
    private String uniqueId;

    @NotEmpty(message = "email cannot be empty")
    @NotBlank(message = "email cannot be blank")
    @GovernmentEmail
    @IsValidJustice
    private String email;

    @NotEmpty(message = "supervisor email cannot be empty")
    @NotBlank(message = "supervisor email be blank")
    @GovernmentEmail
    @IsValidJustice
    private String supervisorEmail;

    @NotEmpty(message = "bvn cannot be empty")
    @NotBlank(message = "bvn cannot be blank")
    @Size(min = 11, message = "bvn should be more than 10 characters")
    private String bvn;

    @NotEmpty(message = "nin cannot be empty")
    @NotBlank(message = "nin cannot be blank")
    @Size(min = 11, message = "nin should be more than 10 characters")
    private String nin;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be a past date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull(message = "Date of engagement is required")
    @Past(message = "Date of engagement must be a past date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfEngagement;
}
