package org.paycorp.librarysystem.controller;

import java.util.List;
import java.util.Optional;

import org.paycorp.helper.ResponseStructure;
import org.paycorp.librarysystem.Exception.DataNotFoundException;
import org.paycorp.librarysystem.dto.Book;
import org.paycorp.librarysystem.dto.User;
import org.paycorp.librarysystem.repository.BookRepository;
import org.paycorp.librarysystem.repository.UserRepository;
import org.paycorp.librarysystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	BookRepository repository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	UserService userservice;
	
	@PostMapping("/adduser")
	public ResponseStructure<User> addUser( @RequestBody User user)
	{
		
		userservice.addUser(user);
		ResponseStructure<User> response=new ResponseStructure<User>();
		response.setStatuscode(HttpStatus.CREATED.value());
		response.setMessage("data saved succesfully");
		response.setData(user);
		return response;
	}
	
	@PutMapping("/update/{id}")
	public ResponseStructure<User> updateuser(@PathVariable int id)
	{
			User user=new User();
			User u=userservice.updateBook(id, user);
			if(u!=null) {
			ResponseStructure<User> response=new ResponseStructure<User>();
			response.setStatuscode(HttpStatus.ACCEPTED.value());
			response.setMessage("data updated succesfully");
			response.setData(user);
			return response;
			}
			else
			{
				throw new DataNotFoundException();
			}
	}
	
	@PostMapping("/borrow/{id}/{bookid}")
	public ResponseStructure<User> borrow(@PathVariable int id, @PathVariable int bookid) {
		Book bid = userrepository.findByBookId(bookid);
		User cid = userrepository.findByUserId(id);
		if (cid!=null) {
			Book book=bid;
			cid.getBooks().add(book);
			book.setUser(cid);
			userrepository.save(cid);
			ResponseStructure<User> structure=new ResponseStructure<User>();
			structure.setData(cid);
			structure.setMessage("borrowed book");
			structure.setStatuscode(HttpStatus.FOUND.value());
			return structure;
		} else {
			throw new DataNotFoundException();
		}
	}
	
		@PutMapping("/return/{id}/{bookid}")
		public ResponseStructure<User>returnbook(@PathVariable int id,@PathVariable int bookid)
			{
				User user=userrepository.findByUserId(id);
				Book book=new Book();
				if(user!=null){
					Book bid = user.getBooks().stream().filter(b->b.getBookid()==bookid).findFirst().orElse(null);
					if(bid!=null){
						user.getBooks().remove(bid);
						userrepository.save(user);
						book.setUser(null);
						ResponseStructure<User> response=new ResponseStructure<User>();
						response.setData(user);
						response.setMessage("return book done");
						response.setStatuscode(HttpStatus.FOUND.value());
						return response;
					}
					else{
						throw new DataNotFoundException();
					}
				}
					else{
						throw new DataNotFoundException();
					}

				}
		}
