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
        //contributionsPage.setData(value);
    }

    @When("Ежемесячное пополнение – int \"(.*)\"")
    public void setMonthlyPayout(int payout) {
        contributionsPage.setMonthlyPayout(payout);
    }

    @When("Отметить – Ежемесячная капитализация")
    public void capitalizationCheck() {
        contributionsPage.checkCapitalization("Ежемесячная капитализация");
    }

    @When("Проверить расчеты по вкладу - Ставка double \"(.*)\"")
    public void checkTheRate(double rate) {
        contributionsPage.checkTheRate(rate);
    }

    @When("Проверить расчеты по вкладу - К снятию через месяц double \"(.*)\"")
    public void checkTheWithdraw (double withdraw) {

    }

    @When("Проверить расчеты по вкладу - Пополнение за 6 месяцев int \"(.*)\"")
    public void checkReplenishment (int value) {

    }

    @When("Проверить расчеты по вкладу - Начислено double \"(.*)\"")
    public void checkAccrued (double value) {

    }
}
