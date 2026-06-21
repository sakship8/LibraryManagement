package org.practice.entity;

import jakarta.persistence.*;
import lombok.*;

import org.practice.enums.BookCategory;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="Book")
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long bookId;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private String bookAuthor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookCategory category;

    @Column(nullable = false)
    private Integer totalCopies;

    @Column(nullable = false)
    private Integer availableCopies;

    @Column(nullable = false, unique = true)
    private String isbn;

}
