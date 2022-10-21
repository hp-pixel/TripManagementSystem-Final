package com.tms.service;

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

import com.tms.entities.Travels;

@ExtendWith({ SpringExtension.class })
@DataJpaTest
@Import(ITravelsServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ITravelsServiceImplTest{

	@Autowired
	private ITravelsService tService;

	@Autowired
	private EntityManager em;

	@Test
	public void addTravels() {
		Travels travel = new Travels("Travels", "Manju", "delhi", "9876543210");
		Travels travelFound = tService.addTravels(travel);
		Assertions.assertEquals(travelFound.getAgentName(), travel.getAgentName());
	}

	@Test
	public void updateTravels() {
		Travels travel = new Travels("Travels", "Manju", "delhi", "9876543210");
		em.persist(travel);
		Travels travelUpdated = new Travels("Travels", "Sanju", "delhi", "9876543210");
		travelUpdated.setTravelsId(travel.getTravelsId());
		Travels travelFound = tService.updateTravels(travelUpdated);
		Assertions.assertEquals(travelFound.getAgentName(), travelUpdated.getAgentName());
	}

	@Test
	public void removeTravels() {
		Travels travel = new Travels("Travels", "Manju", "delhi", "9876543210");
		em.persist(travel);
		Travels travelFound = tService.removeTravels(travel.getTravelsId());
		Assertions.assertEquals(travelFound.getAgentName(), travel.getAgentName());
	}

	@Test
	public void searchTravels() {
		Travels travel = new Travels("Travels", "Manju", "delhi", "9876543210");
		em.persist(travel);
		Travels travelFound = tService.searchTravels(travel.getTravelsId());
		Assertions.assertEquals(travelFound.getAgentName(), travel.getAgentName());
	}

	@Test
	public void viewTravels() {
		Travels travel = new Travels("Travels", "Manju", "delhi", "9876543210");
		em.persist(travel);
		List<Travels> travelFound = tService.viewTravels();
		Assertions.assertNotNull(travelFound);
	}
}
