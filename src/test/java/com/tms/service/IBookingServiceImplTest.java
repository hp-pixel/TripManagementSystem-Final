package com.tms.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tms.entities.Booking;
import com.tms.entities.Customer;
import com.tms.entities.Package;

import com.tms.repository.IBookingRepository;

@ExtendWith({ SpringExtension.class })
@Import(IBookingServiceImpl.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IBookingServiceImplTest {

	@Autowired
	private IBookingRepository bRep;

	@Autowired
	private IBookingService bService;

	@Autowired
	private EntityManager em;

	@Test
	public void makeBooking(){
		Customer cust = new Customer("Prabhnoor", "prabh123", "Delhi", "9810859887", "prabhnoor.kaur@gmail.com");
		em.persist(cust);
		Booking book = new Booking("Business class", "Flying first class", "Business Class", LocalDate.now(),
				cust.getCustomerId());
		Package pack = new Package("package1","Good package","First Class",345.67);
		em.persist(pack);
		book.setPackId(pack.getPackageId());
		Booking bookFound = bService.makeBooking(book);
		Assertions.assertEquals(bookFound.getBookingTitle(), book.getBookingTitle());
	}

	@Test
	public void cancelBooking(){
		Customer cust = new Customer("Prabhnoor", "prabh123", "Delhi", "9810859887", "prabhnoor.kaur@gmail.com");
		em.persist(cust);
		Booking book = new Booking("Business class", "Flying first class", "Business Class", LocalDate.now(),
				cust.getCustomerId());
		em.persist(book);

		Booking bookFound = bService.cancelBooking(book.getBookingId());
		Assertions.assertEquals(bookFound, book);
	}

	@Test
	public void viewBooking(){
		Customer cust = new Customer("Prabhnoor", "prabh123", "Delhi", "9810859887", "prabhnoor.kaur@gmail.com");
		em.persist(cust);
		Booking book = new Booking("Business class", "Flying first class", "Business Class", LocalDate.now(),
				cust.getCustomerId());
		em.persist(book);
		Integer id = book.getBookingId();
		Booking book1 = bService.viewBooking(id);
		Assertions.assertEquals(book1.getDescription(), book.getDescription());
	}

	@Test
	public void viewAllBookings() {
		bRep.deleteAll();
		Customer cust = new Customer("prabhnoor", "prabh123", "felhi", "9810859887", "prabhnoor.kaur@gmail.com");
		em.persist(cust);
		Booking book1 = new Booking("business class", "flying first class", "business class", LocalDate.now(),
				cust.getCustomerId());
		em.persist(book1);
		List<Booking> bookFound = bService.viewAllBookings();
		Assertions.assertNotNull(bookFound);
	}
}
