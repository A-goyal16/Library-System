package org.paycorp.librarysystem.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Book
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_generator")
	@SequenceGenerator(name="book_generator",sequenceName = "PAY",allocationSize = 1001)
	int bookid;
	String author;
	String name;
	@ManyToOne
	User user;
	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", author=" + author + ", name=" + name + "]";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

