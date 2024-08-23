package com.libms.libms.repository;

import com.libms.libms.entity.BookPatron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPatronRepository extends JpaRepository<BookPatron, Long> {
}
