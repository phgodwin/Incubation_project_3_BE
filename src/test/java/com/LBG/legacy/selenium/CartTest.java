package com.LBG.legacy.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

public class CartTest {

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
	void testCreateCart() throws InterruptedException {

		this.driver.get("http://localhost:" + this.port);
		WebElement clickOrders = this.driver.findElement(By.cssSelector("#basic-nav-dropdown > span"));
		clickOrders.click();

		WebElement clickCurrentOrders = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > div > div > a:nth-child(1)"));
		clickCurrentOrders.click();

		WebElement enterCustomer = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form:nth-child(1) > label > input[type=text]"));
		enterCustomer.sendKeys("New Test Business");

		WebElement createCartButton = this.driver
				.findElement(By.cssSelector("#root > div > div > div:nth-child(1) > form:nth-child(1) > button"));
		Thread.sleep(500);
		createCartButton.click();
		Thread.sleep(500);

		Alert cartAlert = driver.switchTo().alert();
		String cartAlertMessage = cartAlert.getText();
		assertEquals("Cart created successfully", cartAlertMessage);
		cartAlert.accept();

	}

	@Test

	void testEditCustomer() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port);
		WebElement clickOrders = this.driver.findElement(By.cssSelector("#basic-nav-dropdown > span"));
		clickOrders.click();

		WebElement clickCurrentOrders = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > div > div > a:nth-child(1)"));
		clickCurrentOrders.click();

		WebElement editCustomer = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div:nth-child(1) > div > div > ul > li:nth-child(2) > button.btn.btn-warning"));
		editCustomer.click();

		Thread.sleep(500);

		Alert alert = driver.switchTo().alert();

		String alertMessage = driver.switchTo().alert().getText();

		System.out.println(alertMessage);
		Thread.sleep(500);
		alert.sendKeys("Edited Customer");
		Thread.sleep(500);
		alert.accept();

		Thread.sleep(500);

		WebElement editedBuyer = this.driver.findElement(
				By.cssSelector("#root > div > div > div.container.mt-4 > div > div:nth-child(1) > div > div > h5"));
		Assertions.assertEquals("1: Edited Customer", editedBuyer.getText());

	}

	@Test

	void addAndDeleteFromCartTest() throws InterruptedException {

		this.driver.get("http://localhost:" + this.port);
		WebElement clickOrders = this.driver.findElement(By.cssSelector("#basic-nav-dropdown > span"));
		clickOrders.click();

		WebElement clickCurrentOrders = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > div > div > a:nth-child(1)"));
		clickCurrentOrders.click();

//		add items 1 and 2

		WebElement clickSelectItem = this.driver.findElement(By.cssSelector(
				"#root > div > div > div:nth-child(1) > form:nth-child(2) > label:nth-child(2) > select > option:nth-child(2)"));
		clickSelectItem.click();
		WebElement clickSelectCustomer = this.driver.findElement(By.cssSelector(
				"#root > div > div > div:nth-child(1) > form:nth-child(2) > label:nth-child(3) > select > option:nth-child(2)"));
		clickSelectCustomer.click();

		WebElement addToCartButton = this.driver
				.findElement(By.cssSelector("#root > div > div > div:nth-child(1) > form:nth-child(2) > button"));
		addToCartButton.click();
		Thread.sleep(500);

		Alert addAlert = driver.switchTo().alert();
		String addAlertMessage = addAlert.getText();
		assertEquals("Item added to cart successfully", addAlertMessage);
		addAlert.accept();
		Thread.sleep(500);

//		delete item

		WebElement removeItem = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div:nth-child(1) > div > div > ul > li:nth-child(2) > button"));
		removeItem.click();
		Thread.sleep(500);

//		check first item listed
		WebElement firstListItem = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div:nth-child(1) > div > div > ul > li:nth-child(2)"));

		Assertions.assertEquals("Edit Customer Delete Cart", firstListItem.getText());
	}

	@Test

	void makeOrderTest() throws InterruptedException {

		this.driver.get("http://localhost:" + this.port);
		WebElement clickOrders = this.driver.findElement(By.cssSelector("#basic-nav-dropdown > span"));
		clickOrders.click();

		WebElement clickCurrentOrders = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > div > div > a:nth-child(1)"));
		clickCurrentOrders.click();

//	add item to cart

		WebElement clickSelectItem = this.driver.findElement(By.cssSelector(
				"#root > div > div > div:nth-child(1) > form:nth-child(2) > label:nth-child(2) > select > option:nth-child(2)"));
		clickSelectItem.click();
		WebElement clickSelectCustomer = this.driver.findElement(By.cssSelector(
				"#root > div > div > div:nth-child(1) > form:nth-child(2) > label:nth-child(3) > select > option:nth-child(2)"));
		clickSelectCustomer.click();

		WebElement addToCartButton = this.driver
				.findElement(By.cssSelector("#root > div > div > div:nth-child(1) > form:nth-child(2) > button"));
		addToCartButton.click();
		Thread.sleep(500);

		Alert addAlert = driver.switchTo().alert();
		String addAlertMessage = addAlert.getText();
		assertEquals("Item added to cart successfully", addAlertMessage);
		addAlert.accept();
		Thread.sleep(500);

// complete order 

		WebElement completeOrderButton = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div:nth-child(1) > div > div > ul > li:nth-child(5) > button"));
		completeOrderButton.click();
		Thread.sleep(500);

		Alert completeAlert = driver.switchTo().alert();
		String completeAlertMessage = completeAlert.getText();
		assertEquals("Order completed successfully!", completeAlertMessage);
		completeAlert.accept();

	}

}
