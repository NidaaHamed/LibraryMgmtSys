package com.libms.libms.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    public Optional<Book> findBook(Long id) {
        return bookRepository.findById(id);
    }
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    public Book updateBook(Long id, Book bookDetails) throws BookNotFoundException {
        Optional<Book> optionalBook= bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(bookDetails.getTitle());
            existingBook.setAuthor(bookDetails.getAuthor());
            existingBook.setPublicationYear(bookDetails.getPublicationYear());
            existingBook.setIsbn(bookDetails.getIsbn());
            return bookRepository.save(existingBook);
        }else {
            throw new BookNotFoundException("Book not found with id: " + id);
        }

    }
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
