package org.practice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateMemberRequest(
        @NotBlank
        String memberName,

        @Email
        String email,

        @NotBlank
        String phoneNo
) {
}
