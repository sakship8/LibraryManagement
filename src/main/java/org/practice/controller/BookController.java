package org.practice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.practice.dto.request.CreateBookRequest;
import org.practice.dto.request.UpdateBookRequest;
import org.practice.dto.response.BookResponse;
import org.practice.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        List<BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long bookId){
        BookResponse response=bookService.getBookById(bookId);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody CreateBookRequest request){
        BookResponse response=bookService.createBook(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long bookId, @Valid @RequestBody UpdateBookRequest request){
        BookResponse response=bookService.updateBook(bookId,request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{bookId}")
    public  ResponseEntity<Void> deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
}
