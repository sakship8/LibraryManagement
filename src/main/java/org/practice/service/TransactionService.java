package org.practice.service;

import org.practice.dto.request.BorrowBookRequest;
import org.practice.dto.response.BorrowingTransactionResponse;

public interface TransactionService {
    BorrowingTransactionResponse borrowBook(BorrowBookRequest request);
}
