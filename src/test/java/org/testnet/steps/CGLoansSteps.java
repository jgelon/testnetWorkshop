package org.testnet.steps;

import org.testnet.ourWebdriver.BrowserFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CGLoansSteps {
    private final WebDriver browser;

    public CGLoansSteps() throws MalformedURLException {
        browser = BrowserFactory.getWebDriver();
    }

    @Given("^I have opened the loan request page$")
    public void iHaveOpenedTheLoanRequestPage() throws Throwable {
        browser.get(System.getProperty("user.dir") + "\\src\\main\\resources\\index.html");
        Thread.sleep(1000);
    }


    @When("^I select loan type 'Car-loan'$")
    public void iSelectLoanTypeCarLoan() throws Throwable {
        browser.findElement(By.cssSelector("input[value='Car-loan']")).click();
        Thread.sleep(1000);
    }

    @When("^the amount I want to borrow is '(\\d+)'$")
    public void theAmountIWantToBorrowIs(final Integer amount) throws Throwable {
        browser.findElement(By.cssSelector("input[name='amount']")).sendKeys(amount.toString());
    }

    @When("^I continue to explanation$")
    public void iContinueToExplanation() throws Throwable {
        Thread.sleep(1000);
        browser.findElement(By.cssSelector("button#verderPage1")).click();
        Thread.sleep(1000);
    }

    @Then("^the \"([^\"]*)\" page is shown$")
    public void thePageIsShown(final String pageTitle) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertThat(browser.findElement(By.cssSelector("#toelichtingscherm")).getText(), is(pageTitle));
    }

    @When("^I select that I have knowledge about loans$")
    public void iSelectThatIHaveKnowledgeAboutLoans() throws Throwable {
        browser.findElement(By.cssSelector("input#kennis_ja")).click();
    }

    @When("^I select that I do not have knowledge about loans$")
    public void iSelectThatIDoNotHaveKnowledgeAboutLoans() throws Throwable {
        browser.findElement(By.cssSelector("input#kennis_nee")).click();
    }

    @And("^I continue to personal data$")
    public void iContinueToPersonalData() throws Throwable {
        Thread.sleep(1000);
        browser.findElement(By.cssSelector("button#verderPage2")).click();
        Thread.sleep(1000);
    }

    @When("^I fill the name as \"([^\"]*)\"$")
    public void iFillTheNameAs(final String name) throws Throwable {
        browser.findElement(By.cssSelector("input#naam")).sendKeys(name);
    }

    @And("^I am a male$")
    public void iAmAMale() throws Throwable {
        browser.findElement(By.cssSelector("label[for='man']")).click();
    }

    @And("^I am born on (\\d+-\\d+-\\d+)$")
    public void iAmBornOn(final String date) throws Throwable {
        browser.findElement(By.cssSelector("input#geboortedatum")).sendKeys(date);
    }

    @And("^I live on \"([^\"]*)\"$")
    public void iLiveOn(final String address) throws Throwable {
        browser.findElement(By.cssSelector("input#adres")).sendKeys(address);
    }

    @And("^my phonenumber is (\\d+)$")
    public void myPhonenumberIs(final Integer phonenumber) throws Throwable {
        browser.findElement(By.cssSelector("input#telefoonnummer")).sendKeys(phonenumber.toString());
    }

    @And("^my backaccountnumber is (\\w+)$")
    public void myBackaccountnumberIsNLABNA(final String bankNumber) throws Throwable {
        browser.findElement(By.cssSelector("input#rekeningnummer")).sendKeys(bankNumber);
    }

    @And("^I have an income of (\\d+)$")
    public void iHaveAnIncomeOf(final Integer income) throws Throwable {
        browser.findElement(By.cssSelector("input#inkomen")).sendKeys(income.toString());
    }

    @And("^I have a housing cost of (\\d+)$")
    public void iHaveAHousingCostOf(final Integer cost) throws Throwable {
        browser.findElement(By.cssSelector("input#woonlasten")).sendKeys(cost.toString());
    }

    @And("^I am living in a rental$")
    public void iAmLivingInARental() throws Throwable {
        browser.findElement(By.cssSelector("select#woonsituatie option[value='rental']")).click();
    }

    @And("^I am married$")
    public void iAmMarried() throws Throwable {
        browser.findElement(By.cssSelector("select#staat option[value='married']")).click();
    }

    @And("^I have a permanent contract$")
    public void iHaveAPermanentContract() throws Throwable {
        browser.findElement(By.cssSelector("select#typeinkomen option[value='permanent contract']")).click();
    }

    @And("^I upload my payment slip$")
    public void iUploadMyPaymentSlip() throws Throwable {
        final String fileLocation = System.getProperty("user.dir") + "\\src\\test\\resources\\paymentslip.txt";
        browser.findElement(By.cssSelector("input#loonstrookje")).sendKeys(fileLocation);
    }

    @And("^I continue to submit the loan request$")
    public void iContinueToSubmitTheLoanRequest() throws Throwable {
        Thread.sleep(1000);
        browser.findElement(By.cssSelector("button#verderPage3")).click();
        Thread.sleep(1000);
    }

    @Then("^I have to confirm my data$")
    public void iHaveToConfirmMyData() throws Throwable {
        assertThat(browser.findElement(By.cssSelector("h2#ingevuldegegevens")).getText(), is("Confirm data"));
        Thread.sleep(5000);
    }
}