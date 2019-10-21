package com.holin.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ContributionsPage extends BasePage {
    @FindBy (xpath = "//div [@class ='calculator__currency-content']/label")
    List <WebElement> chooseCurrency;

    @FindBy (xpath =  "//input[@type='text' and @name = 'amount']")
    WebElement amount;

    @FindBy (xpath = "//select[@class='calculator__slide-input js-slide-value']")
    WebElement timeChoose;

    @FindBy (xpath = "//input [@name='replenish']")
    WebElement monthAdding;

    @FindBy (xpath = "//div[@class='calculator__check-row-field']")
    List<WebElement> checkersOnPage;

    @FindBy (xpath = "//span [@class = 'js-calc-rate']")
    WebElement rateElement;

    @FindBy (xpath = "//span[@class=\"calculator__check-block-input\"]//div//input[@name= 'deposit_b_n_tab']")
    WebElement bottom;

    @FindBy (xpath = "//span [@class='js-calc-result']")
    WebElement accured;

    @FindBy (xpath = "//span [@class='js-calc-replenish']")
    WebElement replenish;

    @FindBy (xpath = "//span [@class='js-calc-earned']")
    WebElement income;

    @Step ("Выбираем {currency}")
    public void chooseTypeOfCurrency(String currency) {
        wait.until(ExpectedConditions.visibilityOf(accured));
        for (WebElement webelement: chooseCurrency) {
            if (webelement.getText().equalsIgnoreCase(currency)) {
                webelement.click();
                return;
            }
        }
    }

    @Step ("Сумма вклада – {numbers}")
    public void setAmount(int numbers) {
        setCurrentValue();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", amount);
        amount.clear();
        amount.sendKeys(String.valueOf(numbers));
        waitForChanges(accured, currentMonthValue);
    }

    @Step ("Срок вклада - {value}")
    public void setData(String value) {
        setCurrentValue();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", timeChoose);
        Select select = new Select(timeChoose);
        select.selectByValue(value.replaceAll("[^0-9]", ""));
        waitForChanges(accured, currentMonthValue);
    }

    @Step ("Ежемесячное пополнение – int {payout}")
    public void setMonthlyPayout(int payout){
        setCurrentValue();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", monthAdding);
        monthAdding.clear();
        monthAdding.sendKeys(String.valueOf(payout));
        waitForChanges(accured, currentMonthValue);
    }

    @Step ("Отметить чекбокс – {nameChecker}")
    public void clickByCheckerFunction(String nameChecker) {
        setCurrentValue();
        for (WebElement element : checkersOnPage) {
            WebElement currentChecker = element.findElement(By.xpath(".//span[@class='calculator__check-text']"));
            if (currentChecker.getText().equalsIgnoreCase(nameChecker)) {
                WebElement elementClick = element.findElement(By.xpath(".//span[@class='calculator__check-block-input']"));
                wait.until(ExpectedConditions.elementToBeClickable(elementClick));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", elementClick);
                elementClick.click();
                waitForChanges(accured, currentMonthValue);
                return;
            }
        }
    }

    @Step ("Проверить расчеты по вкладу - Ставка {rate}")
    public void checkTheRate(double rate) {
        Assert.assertEquals(rate, formatString(rateElement.getText()), 0.0);
    }

    @Step ("Проверить расчеты по вкладу - К снятию через месяц {withdraw}")
    public void checkTheWithdraw (String withdraw) {
        Assert.assertEquals(withdraw, accured.getText().replaceAll("\\s",""));
    }

    @Step ("Проверить расчеты по вкладу - Пополнение за 6 месяцев {value}")
    public void checkReplenishment (int value) {
        Assert.assertTrue(value == formatString(replenish.getText()));
    }

    @Step ("Проверить расчеты по вкладу - Начислено {value}")
    public void checkIncome (String value) {
        Assert.assertEquals(value, income.getText().replaceAll("\\s",""));
    }
}
