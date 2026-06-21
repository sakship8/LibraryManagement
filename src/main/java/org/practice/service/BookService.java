package org.practice.service;

import org.practice.dto.request.CreateBookRequest;
import org.practice.dto.request.UpdateBookRequest;
import org.practice.dto.response.BookResponse;

import java.util.List;

public interface BookService {

    BookResponse createBook(CreateBookRequest request);
    BookResponse getBookById(Long bookId);
    List<BookResponse> getAllBooks();
    BookResponse updateBook(Long bookId, UpdateBookRequest request);
    void deleteBook(Long bookId);

}
