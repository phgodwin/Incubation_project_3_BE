package com.LBG.legacy.selenium;

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
		WebElement clickUserName = this.driver.findElement(By.cssSelector(
				"#root > div > div > div > div > div:nth-child(4) > label:nth-child(2) > input[type=text]"));
		clickUserName.sendKeys("Liam");
		WebElement clickPassword = this.driver.findElement(By.cssSelector(
				"#root > div > div > div > div > div:nth-child(4) > label:nth-child(4) > input[type=password]"));
		clickPassword.sendKeys("Password");
		WebElement clickLogin = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div:nth-child(4) > button"));
		clickLogin.click();
	}

	@Test
	@Order(2)

	void testItem() {
		this.driver.get("http://localhost:" + this.port);
		WebElement clickInventory = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > li:nth-child(2) > a > b"));
		clickInventory.click();
		WebElement enterItemName = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form > label:nth-child(2) > input[type=text]"));
		enterItemName.sendKeys("Tissues");
		WebElement enterItemPrice = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form > label:nth-child(3) > input[type=text]"));
		enterItemPrice.sendKeys("0.99");
		WebElement enterItemQuantity = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form > label:nth-child(4) > input[type=text]"));
		enterItemQuantity.sendKeys("1");
		WebElement createItemButton = this.driver
				.findElement(By.cssSelector("#root > div > div > div:nth-child(1) > form > button"));
		createItemButton.click();
		driver.navigate().refresh();
	}

	@Test
	@Order(3)

	void testCart() {

	}
}
