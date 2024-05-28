package org.paycorp.librarysystem.service;

import java.util.Optional;

import org.paycorp.librarysystem.dto.Book;
import org.paycorp.librarysystem.dto.User;
import org.paycorp.librarysystem.repository.BookRepository;
import org.paycorp.librarysystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
	@Autowired
	UserRepository userrespository;
	
	@Autowired
	BookRepository bookrepository;
	
	
	public User addUser(User user)
	{
		return userrespository.save(user);
	}
	
	public User updateBook(int id, User user) {
        user.setId(id);
        return userrespository.save(user);
	}
	
	
	
	
	
}
