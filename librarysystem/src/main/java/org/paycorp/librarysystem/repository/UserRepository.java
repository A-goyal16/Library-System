package org.paycorp.librarysystem.repository;

import java.util.List;

import org.paycorp.librarysystem.dto.Book;
import org.paycorp.librarysystem.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer>
{

	@Query("select x from Book x where x.name=?1")
	List<Book> findbyname(String name);

	 	@Query("select x from Book x where x.bookid=?1")
		Book findByBookId(int bookid);
		
		@Query("select x from User x where x.id=?1")
		User findByUserId(int id);
	
}
