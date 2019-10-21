package com.holin.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

	private static WebDriver driver;

	public static WebDriver getDriver() {

		switch (TestProperties.getInstance().getProperties().getProperty("browser")) {
			case "chrome" : if (driver == null) {
				System.setProperty(
					TestProperties.getInstance().getProperties().getProperty("chromeDriverName"),
					TestProperties.getInstance().getProperties().getProperty("chromeDriverPath"));
					driver = new ChromeDriver();
				}
				break;
			case "firefox" : if (driver == null) {
				System.setProperty(
					TestProperties.getInstance().getProperties().getProperty("firefoxDriverName"),
					TestProperties.getInstance().getProperties().getProperty("firefoxDriverPath"));
					driver = new FirefoxDriver();
				}
				break;
		}
		return driver;
	}

	public static void close() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
