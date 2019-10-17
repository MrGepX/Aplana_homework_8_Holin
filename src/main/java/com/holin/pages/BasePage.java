package com.holin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.holin.util.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public class BasePage {

	WebDriver driver;
	public static Wait<WebDriver> wait;
	public String currentMonthValue;

	BasePage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
		driver = DriverManager.getDriver();
		wait = new WebDriverWait(driver, 10, 2000);
	}

	public WebElement waitForElement(WebElement element){
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void setCurrentValue() {
		WebElement element = driver.findElement(By.xpath("//span [@class='js-calc-result']"));
		currentMonthValue = element.getText();
	}

	public void waitForChanges(WebElement element, String oldValue) {
		new WebDriverWait(driver, 10).until(new Function<WebDriver, Object>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return !element.getText().equals(oldValue);
			}
		});
	}

	public double formatString(String string) {
		return Double.parseDouble(string.replaceAll("[^0-9.,]", ""));
	}
}
