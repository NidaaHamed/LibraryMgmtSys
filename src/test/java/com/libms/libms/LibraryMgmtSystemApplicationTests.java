package com.libms.libms;

import com.libms.libms.entity.Book;
import com.libms.libms.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class LibraryMgmtSystemApplicationTests {

	@Autowired
	BookService bookService;

	@Test
	void getBookByIdNotFoundTest(){
		Optional<Book> book = bookService.findBook((long) -5);
		assertEquals(false, book.isPresent());
	}
//	@Test
//	void getBookByIdFoundTest(){
//		Optional<Book> book = bookService.findBook((long) 5);
//		assertEquals(true, book.isPresent());
//	}

}
