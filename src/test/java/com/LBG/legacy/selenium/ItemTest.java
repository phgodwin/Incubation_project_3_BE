package com.LBG.legacy.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	void testCreateItem() throws InterruptedException {

		this.driver.get("http://localhost:" + this.port);
		WebElement clickInventory = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > li:nth-child(2) > a > b"));
		clickInventory.click();
		WebElement enterItemName = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form > label:nth-child(2) > input[type=text]"));
		enterItemName.sendKeys("Test course 4");
		WebElement enterItemPrice = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form > label:nth-child(3) > input[type=text]"));
		enterItemPrice.clear();
		enterItemPrice.sendKeys("150.99");
		WebElement enterItemQuantity = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form > label:nth-child(4) > input[type=text]"));
		enterItemQuantity.clear();
		enterItemQuantity.sendKeys("100");
		WebElement createItemButton = this.driver
				.findElement(By.cssSelector("#root > div > div > div:nth-child(1) > form > button"));

		createItemButton.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert = wait.withTimeout(Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());

		String alertMessage = alert.getText();
		assertEquals("Item created successfully", alertMessage);
		alert.accept();

	}

	@Test
	void testUpdateItem() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port);
		WebElement clickInventory = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > li:nth-child(2) > a > b"));
		clickInventory.click();
		WebElement editItemButton = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div > div > div > ul > li:nth-child(2) > button"));
		editItemButton.click();
		WebElement editItemName = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div > div > div > ul > form > input[type=text]:nth-child(1)"));
		editItemName.sendKeys(Keys.chord(Keys.CONTROL, "a"), "Guitar Lessons");

		WebElement editItemPrice = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div > div > div > ul > form > input[type=text]:nth-child(2)"));
		editItemPrice.sendKeys(Keys.chord(Keys.CONTROL, "a"), "78");

		WebElement editItemQuantity = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div > div > div > ul > form > input[type=text]:nth-child(3)"));
		editItemQuantity.sendKeys(Keys.chord(Keys.CONTROL, "a"), "12");

		WebElement saveButton = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div > div > div > ul > form > button:nth-child(4)"));
		saveButton.click();

		Thread.sleep(500);

		WebElement editedName = this.driver
				.findElement(By.cssSelector("#root > div > div > div.container.mt-4 > div > div > div > div > h5"));
		Assertions.assertEquals("1: Guitar lessons", editedName.getText());

		WebElement editedPrice = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div > div > div > ul > li > p:nth-child(1)"));
		Assertions.assertEquals("Price: £78.00", editedPrice.getText());

		WebElement editedQuantity = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div > div > div > ul > li > p:nth-child(2)"));
		Assertions.assertEquals("Quantity: 12", editedQuantity.getText());

	}

	@Test
	void testDeleteItem() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port);
		WebElement clickInventory = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > li:nth-child(2) > a > b"));
		clickInventory.click();
		WebElement deleteItemButton = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div > div > div > ul > li:nth-child(3) > button"));
		deleteItemButton.click();
		Thread.sleep(500);
		WebElement someElementAfterDelete = this.driver
				.findElement(By.cssSelector("#root > div > div > div.container.mt-4 > div > div > div"));
		assertTrue(someElementAfterDelete.isDisplayed(), "the element is not displayed after delete");

	}

}
