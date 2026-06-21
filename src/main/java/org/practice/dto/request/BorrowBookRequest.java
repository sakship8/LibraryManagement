package org.practice.dto.request;

import jakarta.validation.constraints.NotNull;

public record BorrowBookRequest(
        @NotNull(message = "Member ID is required.")
        Long memberId,

        @NotNull(message = "Book ID is required.")
        Long bookId
) {
}
