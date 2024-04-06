package com.libms.libms.book_patron;

import com.libms.libms.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPatronRepository extends JpaRepository<BookPatron, Long> {
}
