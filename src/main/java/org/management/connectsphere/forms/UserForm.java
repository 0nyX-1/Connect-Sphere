package org.management.connectsphere.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Minimum length is required")
    private String name;

    @Email(message = "Email is required")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 3, message = "Minimum length is required")
    private String password;

    @NotBlank(message = "About is required")
    @Size(min = 3, message = "Minimum length is required")
    private String about;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Digits should be 10")
    private String phoneNumber;

}
