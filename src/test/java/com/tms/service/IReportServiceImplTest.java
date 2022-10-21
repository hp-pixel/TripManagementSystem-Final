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

import com.tms.entities.Admin;
import com.tms.entities.Report;

@ExtendWith({ SpringExtension.class })
@DataJpaTest
@Import(IReportServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IReportServiceImplTest {

	@Autowired
	private IReportService rService;

	@Autowired
	private EntityManager em;

	@Test
	public void addReport() {
		Report report = new Report("report1", "summary");
		Admin admin = new Admin("Nandita","nan123","mail2nanditarao@gmail.com","9810857684");
		em.persist(admin);
		report.setAdminId(admin.getAdminId());
		Report reportFound = rService.addReport(report);
		Assertions.assertEquals(reportFound.getReportName(), report.getReportName());
	}

	@Test
	public void deleteReport() {
		Report report = new Report("report1", "summary");
		em.persist(report);
		Report reportFound = rService.deleteReport(report.getReportId());
		Assertions.assertEquals(reportFound.getReportName(), report.getReportName());
	}

	@Test
	public void viewReport() {
		Report report = new Report("report1", "summary");
		em.persist(report);
		Report reportFound = rService.viewReport(report.getReportId());
		Assertions.assertEquals(reportFound.getReportName(), report.getReportName());
	}

	@Test
	public void viewAllReports() {
		Report report = new Report("report1", "summary");
		em.persist(report);
		List<Report> reportFound = rService.viewAllReports();
		Assertions.assertNotNull(reportFound);
	}
}
