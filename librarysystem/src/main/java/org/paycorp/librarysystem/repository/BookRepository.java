package org.paycorp.librarysystem.repository;

import java.util.List;

import org.paycorp.librarysystem.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book,Integer>
{
	@Modifying
    @Query("DELETE FROM Book b WHERE b.id = ?1")
    void deleteBookById( int id);
	
	@Modifying
    @Query("DELETE FROM Book b WHERE b.name = ?1")
	void deleteBookByName(String name);

	List<Book> save(List<Book> books);

	@Query("select x from Book x where x.bookid=?1")
	Book findBookById(int bookid);
	
}
