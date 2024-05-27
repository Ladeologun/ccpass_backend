package com.finclusion.ccppas.auth.dtos;

import com.finclusion.ccppas.auth.validations.GovernmentEmail;
import com.finclusion.ccppas.auth.validations.IsValidJustice;
import com.finclusion.ccppas.auth.validations.JusticeEmailNotYetRegistered;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterRequestDto {

    @Email(message = "email is not valid")
    @GovernmentEmail
    @JusticeEmailNotYetRegistered
    @NotEmpty(message = "email cannot be empty")
    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotEmpty(message = "password cannot be empty")
    @NotBlank(message = "password cannot be blank")
    @Size(min = 8, message = "password should be more than 8 characters")
    private String password;

    @NotEmpty(message = "firstname cannot be empty")
    @NotBlank(message = "firstname cannot be blank")
    private String firstname;

    @NotEmpty(message = "lastname cannot be empty")
    @NotBlank(message = "lastname cannot be blank")
    private String lastname;

    @NotEmpty(message = "bvn cannot be empty")
    @NotBlank(message = "bvn cannot be blank")
    @Size(min = 11, message = "bvn should be more than 10 characters")

    private String bvn;
    @NotEmpty(message = "nin cannot be empty")
    @NotBlank(message = "nin cannot be blank")
    @Size(min = 11, message = "nin should be more than 10 characters")
    private String nin;

    @NotEmpty(message = "supervisor cannot be empty")
    @NotBlank(message = "supervisor cannot be blank")
    @Email(message = "email is not valid")
    @GovernmentEmail
    @IsValidJustice
    private String supervisorEmail;
}
