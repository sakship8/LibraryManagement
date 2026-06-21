package org.practice.service.impl;

import lombok.RequiredArgsConstructor;
import org.practice.dto.request.BorrowBookRequest;
import org.practice.dto.response.BorrowingTransactionResponse;
import org.practice.entity.Book;
import org.practice.entity.BorrowingTransaction;
import org.practice.entity.Member;
import org.practice.exception.BookUnavailableException;
import org.practice.exception.MemberNotFoundException;
import org.practice.repository.BookRepository;
import org.practice.repository.BorrowingTransactionRepository;
import org.practice.repository.MemberRepository;
import org.practice.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final BorrowingTransactionRepository transactionRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;


    @Override
    public BorrowingTransactionResponse borrowBook(BorrowBookRequest request){

        Member member= memberRepository.findById(request.memberId())
                .orElseThrow(()-> new MemberNotFoundException(request.memberId()));

        Book book=bookRepository.findById(request.bookId())
                .orElseThrow(()->new BookUnavailableException(request.bookId()));

        if(book.getAvailableCopies()<=0){
            throw new BookUnavailableException(request.bookId());
        }

        book.setAvailableCopies(book.getAvailableCopies()-1);

        bookRepository.save(book);

        BorrowingTransaction transaction=BorrowingTransaction.builder()
                .member(member)
                .book(book)
                .borrowDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .returned(false)
                .build();

        BorrowingTransaction savedTransaction=transactionRepository.save(transaction);

        return mapToResponse(savedTransaction);
    }

    public BorrowingTransactionResponse mapToResponse(BorrowingTransaction transaction){
        return  new BorrowingTransactionResponse(
                transaction.getTransactionId(),
                transaction.getMember().getMemberId(),
                transaction.getMember().getMemberName(),
                transaction.getBook().getBookId(),
                transaction.getBook().getBookTitle(),
                transaction.getBorrowDate(),
                transaction.getDueDate(),
                transaction.getReturned()
        );
    }
}
