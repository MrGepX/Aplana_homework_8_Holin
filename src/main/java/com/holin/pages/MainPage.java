package com.holin.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage extends BasePage{

	@FindBy(xpath = "//div[@class='service']//div[@class='service__title']//a [@href = '/contributions/']")
	WebElement contributions;

	@Step("Перейти в меню – Вклады")
	public void clickByText(String text) {
		contributions.click();
	}
}
