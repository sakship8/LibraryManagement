package org.practice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.practice.enums.BookCategory;

public record UpdateBookRequest(
        @NotBlank(message = "Book title is required")
        String bookTile,
        @NotBlank (message = "Book Author is required")
        String bookAuthor,
        @NotBlank (message = "ISBN is required")
        String isbn,
        @NotNull (message = "Category is required")
        BookCategory category,
        @NotNull(message = "Total copies is required")
        @Min(value = 1, message = "Total copies must be at least 1")
        Integer totalCopies

        ) {
}
