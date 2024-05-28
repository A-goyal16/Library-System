package org.paycorp.librarysystem.service;

import java.util.List;
import java.util.Optional;

import org.paycorp.librarysystem.dto.Book;
import org.paycorp.librarysystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService 
{
	@Autowired
	BookRepository bookrepository;
	
	public Book addBook(Book book) {
        return bookrepository.save(book);
    }


	public List<Book> getAllBooks() {
		return bookrepository.findAll();
	}
	
	public Book updateBook(int bookid, Book book) {
        book.setBookid(bookid);
        return bookrepository.save(book);
	}

	public void deleteBook(int id)
	{
		bookrepository.deleteBookById(id);
	}
	
	public void deleteBookbyname(String name)
	{
		bookrepository.deleteBookByName(name);
	}

	
	
}


