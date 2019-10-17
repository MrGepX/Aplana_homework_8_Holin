package com.holin.steps;

import com.holin.pages.ContributionsPage;
import com.holin.pages.MainPage;
import com.holin.util.DriverManager;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;


public class PageSteps {
    MainPage mainPage;
    ContributionsPage contributionsPage;
    WebDriver driver;


    @When("Перейти в меню – Вклады")
    public void testCase() {
        driver = DriverManager.getDriver();

        mainPage = new MainPage();
        mainPage.clickByText("Вклады");
    }

    @When("Выбрать – Рубли")
    public void contributionCurrency() {
        contributionsPage = new ContributionsPage();
        contributionsPage.chooseTypeOfCurrency();
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

    @When("Отметить частичное снятие – String \"(.*)\"")
    public void withdrawalСheck(String choose) {
        contributionsPage.clickByCheckerFunction(choose);
    }

    @When("Отметить капитализацию – String \"(.*)\"")
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
