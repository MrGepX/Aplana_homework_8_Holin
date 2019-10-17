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
    @FindBy (xpath = "//span [@class='calculator__currency-field-text' and text() = 'Рубли']")
    WebElement chooseCurrency;

    @FindBy (xpath =  "//input[@type='text' and @name = 'amount']")
    WebElement amount;

    @FindBy (xpath = "//div [@class='jq-selectbox__select-text']")
    WebElement timeChoose;

    @FindBy (xpath = "//input [@name='replenish']")
    WebElement monthAdding;

    @FindBy (xpath = "//div[@class='calculator__check-row-field']")
    List<WebElement> checkersOnList;

    @FindBy (xpath = "//span [@class = 'js-calc-rate']")
    WebElement rateElement;

    @FindBy (xpath = "//span[@class=\"calculator__check-block-input\"]//div//input[@name= 'deposit_b_n_tab']")
    WebElement bottom;

    @FindBy (xpath = "//span [@class = 'js-calc-result']")
    WebElement accured;

    @Step ("Выбираем валюту")
    public void chooseTypeOfCurrency() { //TODO Выбор валюты
        wait.until(ExpectedConditions.visibilityOf(accured));
        chooseCurrency.click();
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
        System.out.println(select.getAllSelectedOptions());
        select.selectByValue(value);
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

    @Step ("Отметить – Ежемесячная капитализация") //TODO
    public void checkCapitalization(String nameChecker) {
        setCurrentValue();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", bottom);
        for (WebElement element : checkersOnList) {
            WebElement nameOfCheker = element.findElement(By.xpath(".//span[@class='calculator__check-text']"));
            if (nameOfCheker.getText().equalsIgnoreCase(nameChecker)) {
                WebElement elementClick = element.findElement(By.xpath(".//span[@class=\"calculator__check-block-input\"]"));
                elementClick.click();
                waitForChanges(element, currentMonthValue);
                return;
            }
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", amount);
    }

    @Step ("Проверить расчеты по вкладу - Ставка {rate}")
    public void checkTheRate(double rate) {
        Assert.assertTrue(rate == formatString(rateElement.getText()));
    }

    @Step ("Проверить расчеты по вкладу - К снятию через месяц {withdraw}")
    public void checkTheWithdraw (double withdraw) {

    }

    @Step ("Проверить расчеты по вкладу - Пополнение за 6 месяцев {value}")
    public void checkReplenishment (int value) {

    }

    @Step ("Проверить расчеты по вкладу - Начислено {value}")
    public void checkAccrued (double value) {

    }
}
