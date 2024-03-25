package com.LBG.legacy.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:shopping-schema.sql",
		"classpath:shopping-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class ItemTest {

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
	void testItem() throws InterruptedException {

		this.driver.get("http://localhost:" + this.port);
		WebElement clickInventory = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > li:nth-child(2) > a > b"));
		clickInventory.click();
		WebElement enterItemName = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form > label:nth-child(2) > input[type=text]"));
		enterItemName.sendKeys("T");
		WebElement enterItemPrice = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form > label:nth-child(3) > input[type=text]"));
		enterItemPrice.clear();
		enterItemPrice.sendKeys("1");
		WebElement enterItemQuantity = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form > label:nth-child(4) > input[type=text]"));
		enterItemQuantity.clear();
		enterItemQuantity.sendKeys("1");
		WebElement createItemButton = this.driver
				.findElement(By.cssSelector("#root > div > div > div:nth-child(1) > form > button"));

		createItemButton.click();
		Thread.sleep(500);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert = wait.withTimeout(Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());

		String alertMessage = alert.getText();
		assertEquals("Item created successfully", alertMessage);
		alert.accept();

	}

}
