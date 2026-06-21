package org.practice.dto.response;

import java.time.LocalDate;

public record BorrowingTransactionResponse(
        Long transactionId,
        Long memberId,
        String memberName,
        Long bookId,
        String bookTitle,
        LocalDate borrowDate,
        LocalDate dueDate,
        Boolean returned
) {
}
