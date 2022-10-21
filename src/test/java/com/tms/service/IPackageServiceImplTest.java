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
import com.tms.entities.Hotel;
import com.tms.entities.Package;
import com.tms.service.IPackageServiceImpl;

@ExtendWith({ SpringExtension.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import(IPackageServiceImpl.class)
public class IPackageServiceImplTest {

	@Autowired
	private IPackageService pService;

	@Autowired
	private EntityManager em;

//	@Test
//	public void addPackage() {
//		Customer cust = new Customer("nandita", "nan123", "delhi", "9810783465", "mail2nanditarao@gmail.com");
//		em.persist(cust);
//		Booking book = new Booking("business class", "this is first class", "booking1", LocalDate.now(),
//				cust.getCustomerId());
//		Hotel hotel = new Hotel(1,"hayat", "luxury", "5 star hotel", "delhi", 6000.0, "Booked");
//		Package pack = new Package("package1", "luxury package", "luxury", 7080.9);
//		pack.setBooking(book);
//		pack.setHotel(hotel);
//		Package packFound = pService.addPackage(pack);
//		Assertions.assertEquals(packFound.getPackageName(), pack.getPackageName());
//	}

	@Test
	public void deletePackage(){
		Package pack = new Package("package1", "luxury package", "luxury", 7080.9);
		em.persist(pack);
		Package packFound = pService.deletePackage(pack.getPackageId());
		Assertions.assertEquals(packFound.getPackageName(), pack.getPackageName());
	}

	@Test
	public void searchPackage(){
		Package pack = new Package("package1", "luxury package", "luxury", 7080.9);
		em.persist(pack);
		Package packFound = pService.searchPackage(pack.getPackageId());
		Assertions.assertEquals(packFound.getPackageName(), pack.getPackageName());
	}

	@Test
	public void viewAllPackages() {
		Package pack = new Package("package1", "luxury package", "luxury", 7080.9);
		em.persist(pack);
		List<Package> packFound = pService.viewAllPackages();
		Assertions.assertNotNull(packFound);
	}
}
