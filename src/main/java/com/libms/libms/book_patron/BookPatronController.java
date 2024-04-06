package com.libms.libms.book_patron;

import com.libms.libms.books.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookPatronController {
    private final BookPatronService bookPatronService;

    @Autowired
    public BookPatronController(BookPatronService bookPatronService) {
        this.bookPatronService = bookPatronService;
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(
            @PathVariable("bookId") Long bookId,
            @PathVariable("patronId") Long patronId) {
        try {
            bookPatronService.borrowBook(bookId, patronId);
            return ResponseEntity.ok("Book borrowed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to borrow book: " + e.getMessage());
        }
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(
            @PathVariable("bookId") Long bookId,
            @PathVariable("patronId") Long patronId) {
        try {
            bookPatronService.returnBook(bookId, patronId);
            return ResponseEntity.ok("Book returned successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to return book: " + e.getMessage());
        }
    }
}
