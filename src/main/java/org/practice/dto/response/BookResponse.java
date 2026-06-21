package org.practice.dto.response;

import org.practice.enums.BookCategory;

public record BookResponse(
        Long bookId,
        String bookTitle,
        String bookAuthor,
        String isbn,
        BookCategory category,
        Integer totalCopies,
        Integer availableCopies
) {


}
