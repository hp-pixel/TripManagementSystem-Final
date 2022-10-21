package com.tms.service;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tms.entities.Customer;
import com.tms.entities.User;
import com.tms.service.IUserServiceImpl;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@Import(IUserServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IUserServiceImplTest {

	@Autowired
	private IUserService uService;
	
	@Autowired 
	private EntityManager em;
	
	@Test
	public void addNewUser() {
		Customer cust = new Customer("parul","par123","faridabad","9876543210","parul@gmail.com"); 
		User user = new User("customer","par123");
		user.setCustomer(cust);
		User userFound = uService.addNewUser(user);
		Assertions.assertEquals(userFound.getCustomer(), user.getCustomer());
		Assertions.assertEquals(userFound.getPassword(), user.getPassword());
	}
	
//	@Test
//	public void signIn() {
//		Customer cust = new Customer("parul","par123","faridabad","9876543210","parul@gmail.com"); 
//		User user = new User("customer","par123");
//		user.setCustomer(cust);
//		em.persist(user);
//		User userFound = uService.signIn(user);
//		Assertions.assertEquals(userFound.getPassword(), user.getPassword());
//	}
	
	@Test
	public void signOut() {
		Customer cust = new Customer("parul","par123","faridabad","9876543210","parul@gmail.com"); 
		User user = new User("customer","par123");
		user.setCustomer(cust);
		em.persist(user);
		User userFound = uService.signOut(user);
		Assertions.assertEquals(userFound.getPassword(), user.getPassword());
		
	}
}
