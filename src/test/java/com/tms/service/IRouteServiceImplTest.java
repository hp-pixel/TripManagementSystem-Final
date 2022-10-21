package com.tms.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.tms.entities.Bus;
import com.tms.entities.Route;

@ExtendWith({ SpringExtension.class })
@Import(IRouteServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class IRouteServiceImplTest {

	@Autowired
	private IRouteService rService;

	@Autowired
	private EntityManager em;

	@Test
	public void addRoute() {
		Route route = new Route("delhi", "faridabad", LocalDateTime.now(), LocalDateTime.now(), LocalDate.now(),
				"delhi", 300.0);
		Bus bus1 = new Bus("dtc", "567", 20);
		Bus bus2 = new Bus("travels", "789", 30);
		List<Bus> buses = new ArrayList<>();
		buses.add(bus1);
		buses.add(bus2);

		Route routeFound = rService.addRoute(route);
		Assertions.assertEquals(routeFound.getRouteFrom(), route.getRouteFrom());
	}

	@Test
	public void updateRoute() {
		Route route = new Route("delhi", "faridabad", LocalDateTime.now(), LocalDateTime.now(), LocalDate.now(),
				"delhi", 300.0);
		em.persist(route);
		Route routeUpdate = new Route("delhi", "noida", LocalDateTime.now(), LocalDateTime.now(), LocalDate.now(),
				"delhi", 300.0);
		routeUpdate.setRouteId(route.getRouteId());

		Route routeFound = rService.updateRoute(routeUpdate);
		Assertions.assertEquals(routeFound.getRouteTo(), routeUpdate.getRouteTo());
	}

	@Test
	public void removeRoute() {
		Route route = new Route("delhi", "faridabad", LocalDateTime.now(), LocalDateTime.now(), LocalDate.now(),
				"delhi", 300.0);
		em.persist(route);
		Route routeFound = rService.removeRoute(route.getRouteId());
		Assertions.assertEquals(routeFound.getRouteTo(), route.getRouteTo());
	}

	@Test
	public void searchRoute() {
		Route route = new Route("delhi", "faridabad", LocalDateTime.now(), LocalDateTime.now(), LocalDate.now(),
				"delhi", 300.0);
		em.persist(route);
		Route routeFound = rService.searchRoute(route.getRouteId());
		Assertions.assertEquals(routeFound.getRouteTo(), route.getRouteTo());
	}

	public void viewRouteList() {
		Route route = new Route("delhi", "faridabad", LocalDateTime.now(), LocalDateTime.now(), LocalDate.now(),
				"delhi", 300.0);
		em.persist(route);
		List<Route> routeFound = rService.viewRouteList();
		Assertions.assertNotNull(routeFound);
	}
}
