package com.holin.steps;

import com.holin.pages.ContributionsPage;
import com.holin.pages.MainPage;
import com.holin.util.DriverManager;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;


public class PageSteps {
    MainPage mainPage = new MainPage();
    ContributionsPage contributionsPage = new ContributionsPage();
    WebDriver driver;

    @When("Перейти в меню – \"(.*)\"")
    public void testCase(String menuOption) {
        driver = DriverManager.getDriver();
        mainPage.clickByText(menuOption);
    }

    @When("Выбрать валюту – \"(.*)\"")
    public void contributionCurrency(String currency) {
        contributionsPage.chooseTypeOfCurrency(currency);
    }

    @When("Сумма вклада – int \"(.*)\"")
    public void setAmount(int amount) {
        contributionsPage.setAmount(amount);
    }

    @When("Срок - String \"(.*)\"")
    public void setLong(String value) {
        contributionsPage.setData(value);
    }

    @When("Ежемесячное пополнение – int \"(.*)\"")
    public void setMonthlyPayout(int payout) {
        contributionsPage.setMonthlyPayout(payout);
    }

    @When("Отметить чекбокс – String \"(.*)\"")
    public void capitalizationCheck(String choose) {
        contributionsPage.clickByCheckerFunction(choose);
    }

    @When("Проверить расчеты по вкладу - Ставка double \"(.*)\"")
    public void checkTheRate(double rate) {
        contributionsPage.checkTheRate(rate);
    }

    @When("Проверить расчеты по вкладу - К снятию через месяц String \"(.*)\"")
    public void checkTheWithdraw (String withdraw) {
        contributionsPage.checkTheWithdraw(withdraw);
    }

    @When("Проверить расчеты по вкладу - Пополнение за 6 месяцев int \"(.*)\"")
    public void checkReplenishment (int value) {
        contributionsPage.checkReplenishment(value);
    }

    @When("Проверить расчеты по вкладу - Начислено String \"(.*)\"")
    public void checkAccrued (String value) {
        contributionsPage.checkIncome(value);
    }
}
