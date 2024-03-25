package com.LBG.legacy.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:shopping-schema.sql",
		"classpath:shopping-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class ProjectTest {

	private RemoteWebDriver driver;

	@LocalServerPort
	private int port;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	};

	@Test
	@Order(1)
	void testLogin() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port);
		WebElement clickUserName = this.driver.findElement(By.cssSelector(
				"#root > div > div > div > div > div:nth-child(3) > label:nth-child(2) > input[type=text]"));
		clickUserName.sendKeys("Lucy");
		WebElement clickPassword = this.driver.findElement(By.cssSelector(
				"#root > div > div > div > div > div:nth-child(3) > label:nth-child(4) > input[type=password]"));
		clickPassword.sendKeys("Password");
		WebElement clickLogin = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div:nth-child(3) > button"));
		clickLogin.click();
		Thread.sleep(500);
		WebElement clickNextQuote = this.driver.findElement(By.cssSelector(
				"#root > div > div > div > div.carousel.slide > a.carousel-control-next > span.carousel-control-next-icon"));
		clickNextQuote.click();

		WebElement someElementAfterLogin = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div.card.border-danger > div"));
		assertTrue(someElementAfterLogin.isDisplayed(), "the element is not displayed after login");

	}

}
