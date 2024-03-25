package com.LBG.legacy.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Alert;
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

public class MiscTest {

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

	@Test
	void testPastOrderDisplay() throws InterruptedException {

		this.driver.get("http://localhost:" + this.port);
		WebElement clickOrders = this.driver.findElement(By.cssSelector("#basic-nav-dropdown > span"));
		clickOrders.click();

		WebElement clickCurrentOrders = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > div > div > a:nth-child(1)"));
		clickCurrentOrders.click();

		WebElement completeOrderButton = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div:nth-child(3) > div > div > ul > li:nth-child(5) > button"));
		completeOrderButton.click();
		Thread.sleep(500);

		Alert completeAlert = driver.switchTo().alert();
		String completeAlertMessage = completeAlert.getText();
		assertEquals("Order completed successfully!", completeAlertMessage);
		completeAlert.accept();

		Thread.sleep(500);
		clickOrders.click();

		WebElement clickPastOrders = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > div > div > a:nth-child(2)"));
		clickPastOrders.click();

		WebElement businessName = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div > div > div > h5"));
		Assertions.assertEquals("1: Test business 3", businessName.getText());

		WebElement purchased = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div > div > div > ul > li > p"));
		Assertions.assertEquals("Purchased: Test course 4", purchased.getText());

	}

	@Test

	void testTotalPrice() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port);

		this.driver.get("http://localhost:" + this.port);
		WebElement clickOrders = this.driver.findElement(By.cssSelector("#basic-nav-dropdown > span"));
		clickOrders.click();

		WebElement clickCurrentOrders = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > div > div > a:nth-child(1)"));
		clickCurrentOrders.click();

		WebElement clickCalculateTotal = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div:nth-child(3) > div > div > ul > li:nth-child(4) > button"));
		clickCalculateTotal.click();

		Thread.sleep(500);

		Alert totalAlert = driver.switchTo().alert();
		String totalAlertMessage = totalAlert.getText();
		assertEquals("Total for Test business 3's cart: £11.79", totalAlertMessage);
		totalAlert.accept();

	}

}
