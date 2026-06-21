package org.practice.service.impl;

import lombok.RequiredArgsConstructor;
import org.practice.dto.request.CreateBookRequest;
import org.practice.dto.request.UpdateBookRequest;
import org.practice.dto.response.BookResponse;
import org.practice.entity.Book;
import org.practice.exception.BookNotFoundException;
import org.practice.exception.DuplicateIsbnException;
import org.practice.repository.BookRepository;
import org.practice.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponse createBook(CreateBookRequest request){

        if(bookRepository.findByIsbn(request.isbn()).isPresent()){
            throw new DuplicateIsbnException(request.isbn());
        }

        Book book= Book.builder()
                .bookTitle(request.bookTitle())
                .bookAuthor(request.bookAuthor())
                .isbn(request.isbn())
                .category(request.category())
                .totalCopies(request.totalCopies())
                .availableCopies(request.totalCopies())
                .build();
        Book saveBook= bookRepository.save(book);

        return new BookResponse(
                saveBook.getBookId(),
                saveBook.getBookTitle(),
                saveBook.getBookAuthor(),
                saveBook.getIsbn(),
                saveBook.getCategory(),
                saveBook.getTotalCopies(),
                saveBook.getAvailableCopies()
        );
    }

    @Override
    public BookResponse getBookById(Long bookId){
        Book book=bookRepository.findById(bookId)
                .orElseThrow(()->
                        new BookNotFoundException(bookId));

        return new BookResponse(
                book.getBookId(),
                book.getBookTitle(),
                book.getBookAuthor(),
                book.getIsbn(),
                book.getCategory(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        );
    }

    @Override
    public List<BookResponse> getAllBooks(){

        List<Book> books= bookRepository.findAll();
        return books.stream()
                .map(book-> new BookResponse(
                        book.getBookId(),
                        book.getBookTitle(),
                        book.getBookTitle(),
                        book.getIsbn(),
                        book.getCategory(),
                        book.getTotalCopies(),
                        book.getAvailableCopies()
                )).toList();
    }

    @Override
    public BookResponse updateBook(Long bookId, UpdateBookRequest request){
        Book book=bookRepository.findById(bookId)
                .orElseThrow(()->new BookNotFoundException(bookId));

        book.setBookTitle(request.bookTile());
        book.setBookAuthor(request.bookAuthor());
        book.setCategory(request.category());

        Integer oldTotalCopies=book.getTotalCopies();
        Integer oldAvailableCopies=book.getAvailableCopies();

        book.setTotalCopies(request.totalCopies());

        int diff=request.totalCopies()-oldTotalCopies;
        book.setAvailableCopies(diff+oldAvailableCopies);

        Book updateBook=bookRepository.save(book);

        return mapToResponse(updateBook);
    }

    @Override
    public void deleteBook(Long bookId){
        if(!bookRepository.existsById(bookId)){
            throw new BookNotFoundException(bookId);
        }
        bookRepository.deleteById(bookId);
    }

    private BookResponse mapToResponse(Book book) {

        return new BookResponse(
                book.getBookId(),
                book.getBookTitle(),
                book.getBookAuthor(),
                book.getIsbn(),
                book.getCategory(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        );
    }
}
