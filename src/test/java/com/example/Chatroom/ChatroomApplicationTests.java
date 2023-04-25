package com.example.Chatroom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatroomApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private Environment environment;

	@Test
	public void contextLoads() throws Exception {
		String seleniumDriver = environment.getProperty("selenium.chromedriver");
		System.setProperty("webdriver.chrome.driver", seleniumDriver);   // fetches local path of chromedriver.exe as set in application.yml

		WebDriver driver = new ChromeDriver();
		driver.get(createURLWithPort());

		WebDriver driver2 = new ChromeDriver();
		driver2.get(createURLWithPort());

		assertEquals(driver.getTitle(), "Chat Room Login");

		assertEquals(driver2.getTitle(), "Chat Room Login");

		WebElement textBox = driver.findElement(By.id("username"));
		WebElement linkClass = driver.findElement(By.className("submit"));

		textBox.sendKeys("User1");
		linkClass.click();

		WebElement textBox2 = driver2.findElement(By.id("username"));
		WebElement linkClass2 = driver2.findElement(By.className("submit"));

		textBox2.sendKeys("User2");
		linkClass2.click();

		Thread.sleep(5000);

		assertEquals(driver.getCurrentUrl(), createURLWithPortParm("/chat?username=User1"));

		assertEquals(driver2.getCurrentUrl(), createURLWithPortParm("/chat?username=User2"));

		WebElement username = driver.findElement(By.id("username"));
		assertEquals(username.getText(), "User1");

		WebElement username2 = driver2.findElement(By.id("username"));
		assertEquals(username2.getText(), "User2");

		Thread.sleep(5000);

		WebElement chatnum = driver.findElement(By.className("chat-num"));
		assertEquals(chatnum.getText(), "2");

		WebElement chatnum2 = driver2.findElement(By.className("chat-num"));
		assertEquals(chatnum2.getText(), "2");

		WebElement msg = driver.findElement(By.id("msg"));
		WebElement chatLink = driver.findElement(By.xpath("//button[contains(.,'Send (enter)')]"));
		msg.sendKeys("Hi, good to talk to you");
		chatLink.click();

		Thread.sleep(2000);

		WebElement chatMsg = driver.findElement(By.className("message-content"));
		assertEquals(chatMsg.getText(), "User1：Hi, good to talk to you");

		WebElement chatMsg2 = driver2.findElement(By.className("message-content"));
		assertEquals(chatMsg2.getText(), "User1：Hi, good to talk to you");

		Thread.sleep(2000);

		WebElement msg2 = driver2.findElement(By.id("msg"));
		WebElement chatLink2 = driver2.findElement(By.xpath("//button[contains(.,'Send (enter)')]"));
		msg2.sendKeys("Hello, how are you?");
		chatLink2.click();

		Thread.sleep(2000);

		List<WebElement> elements = driver.findElements(By.cssSelector(".message-container > div"));
		assertEquals(elements.get(0).getText(), "User1：Hi, good to talk to you");
		assertEquals(elements.get(1).getText(), "User2：Hello, how are you?");

		List<WebElement> elements2 = driver2.findElements(By.cssSelector(".message-container > div"));
		assertEquals(elements2.get(0).getText(), "User1：Hi, good to talk to you");
		assertEquals(elements2.get(1).getText(), "User2：Hello, how are you?");

		WebElement clearLink = driver.findElement(By.xpath("//button[contains(.,'Clear')]"));
		clearLink.click();

		boolean cleared = false;
		try {
			driver.findElement(By.className("message-content"));
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			cleared = true;
		}
		assertTrue(cleared);

		driver2.close();

		Thread.sleep(2000);

		assertEquals(chatnum.getText(), "1");

		driver.close();
	}
	private String createURLWithPort() { return "http://localhost:" + port; }
	private String createURLWithPortParm(String uri) { return "http://localhost:" + port + uri; }
}
