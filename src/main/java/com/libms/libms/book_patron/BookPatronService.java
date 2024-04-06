package com.libms.libms.book_patron;

import com.libms.libms.books.Book;
import com.libms.libms.books.BookRepository;
import com.libms.libms.patrons.Patron;
import com.libms.libms.patrons.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookPatronService {
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    @Autowired
    public BookPatronService(BookRepository bookRepository, PatronRepository patronRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }
    @Transactional
    public void borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found with id: " + patronId));

        if (book.getBorrowedBy().contains(patron)) {
            throw new RuntimeException("Book is already borrowed by the patron.");
        }

        book.getBorrowedBy().add(patron);
        bookRepository.save(book);
    }
    @Transactional
    public void returnBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found with id: " + patronId));

        if (!book.getBorrowedBy().contains(patron)) {
            throw new RuntimeException("Book is not borrowed by the patron.");
        }

        // Remove patron from the borrowedBy set in Book entity
        book.getBorrowedBy().remove(patron);
        bookRepository.save(book);
    }
}
