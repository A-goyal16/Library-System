package org.paycorp.librarysystem.controller;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.paycorp.helper.ResponseStructure;
import org.paycorp.librarysystem.Exception.DataNotFoundException;
import org.paycorp.librarysystem.dto.Book;
import org.paycorp.librarysystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;

@RestController
public class MyController
{
	@Autowired
	BookService bookservice;
	
	@PostMapping("/addbooks")
	public ResponseStructure<Book> addBook(@RequestBody Book book)
	{
		bookservice.addBook(book);
		ResponseStructure<Book>response=new ResponseStructure<Book>();
		response.setStatuscode(HttpStatus.ACCEPTED.value());
		response.setData(book);
		response.setMessage("Data Saved Succesfully");
		return response;
	}
	@GetMapping("/getallbook")
	public List<Book> getAllBook()
	{
		return bookservice.getAllBooks();
	}
	
	@GetMapping("/fetchbyauthor/{author}")
	 public ResponseStructure<List<Book>> getBookByAuthor(@PathVariable String author)
	 {
		 List<Book>books=bookservice.getAllBooks();
		 List<Book>allbooks= books.stream().filter(book->book.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
		 if(allbooks.size()>0)
		 {
			 ResponseStructure<List<Book>> response=new ResponseStructure<List<Book>>();
			 response.setStatuscode(HttpStatus.FOUND.value());
			 response.setMessage("data found");
			 response.setData(allbooks);
			 return response;
		 }
		 else
		 {
			 throw new DataNotFoundException();
		 }
		
	 }
	 @GetMapping("/fetchbyname/{name}")
	 public ResponseStructure<List<Book>> getBookByName(@PathVariable String name)
	 {
		 List<Book> books=bookservice.getAllBooks();
		 List<Book> allbooks=books.stream().filter(book->book.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
		 if(allbooks.size()>0)
		 {
			 ResponseStructure<List<Book>> response=new ResponseStructure<List<Book>>();
			 response.setStatuscode(HttpStatus.FOUND.value());
			 response.setMessage("data found");
			 response.setData(allbooks);
			 return response;
		 }
		 else
		 {
			 throw new DataNotFoundException();
		 }
	 }
	 
	 @DeleteMapping("/deletebook/{bookid}")
	 @Transactional
	 public ResponseStructure<Book> deleteBook(@PathVariable int bookid)
	 {
		 bookservice.deleteBook(bookid);
		 ResponseStructure<Book>response=new ResponseStructure<Book>();
		 response.setStatuscode(HttpStatus.OK.value());
		 response.setMessage("delete succesfully");
		 return response;
	 }
	 
	 @DeleteMapping("/deletebookname/{name}")
	 @Transactional
	 public ResponseStructure<Book> deletebookname(@PathVariable String name)
	 {
		 bookservice.deleteBookbyname(name);
		 ResponseStructure<Book> response=new ResponseStructure<Book>();
		 response.setStatuscode(HttpStatus.OK.value());
		 response.setMessage("delete succesfully");
		 return response;
	 }
	 
	 
	 @PutMapping("/updatebook/{bookid}")
	    public ResponseStructure<Book> updateBook(@PathVariable int bookid, @RequestBody Book book) {
	         bookservice.updateBook(bookid, book);
	         ResponseStructure<Book>response=new ResponseStructure<Book>();
	         response.setStatuscode(HttpStatus.FOUND.value());
	         response.setData(book);
	         response.setMessage("Data Updated Succesfully");
	         return response;
	    }
}
