package com.LBG.legacy.selenium;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.LBG.legacy.SpringLegacyApplication;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = { SpringLegacyApplication.class })
@TestMethodOrder(OrderAnnotation.class)

public class ProjectTest {

	private RemoteWebDriver driver;
//	private WebDriverWait wait;

	@LocalServerPort
	private int port;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test
	@Order(1)

	void testLogin() {
		this.driver.get("http://localhost:" + this.port);
	}
}
