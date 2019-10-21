package com.holin.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MainPage extends BasePage{

	@FindBy(xpath = "//div [@class = 'services services_main']/div/div")
	List<WebElement> contributions;

	@Step("Перейти в меню – Вклады")
	public void clickByText(String text) {
		for (WebElement webElement: contributions) {
			if (webElement.getText().equalsIgnoreCase(text)) {
				webElement.click();
				return;
			}
		}
	}
}
