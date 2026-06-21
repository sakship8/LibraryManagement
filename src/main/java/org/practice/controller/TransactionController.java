package org.practice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.practice.dto.request.BorrowBookRequest;
import org.practice.dto.response.BorrowingTransactionResponse;
import org.practice.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingTransactionResponse> borrowBook(@Valid @RequestBody BorrowBookRequest request){
        BorrowingTransactionResponse response = transactionService.borrowBook(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }



}
