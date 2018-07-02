package org.testnet.steps;

import org.testnet.ourWebdriver.BrowserFactory;
import org.testnet.ourWebdriver.OurWebDriver;
import com.github.javafaker.Faker;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CGLoanEx10Steps {
    private final OurWebDriver browser;

    public CGLoanEx10Steps() throws MalformedURLException {
        browser = BrowserFactory.getWebDriver();
    }

    @Given("^the customer wants information about a loan$")
    public void theCustomerWantsInformationAboutALoan() throws Throwable {
        browser.get(System.getProperty("user.dir") + "\\src\\main\\resources\\index.html");
        Thread.sleep(1000);
    }

    @When("^the customer wants a loan for \"([^\"]*)\"$")
    public void theCustomerWantsALoanFor(final String loanType) throws Throwable {
        final List<WebElement> myListOfElements = browser.findElements(By.cssSelector("div[ng-hide='page!=1'] button.btn"));
        for (final WebElement mySingleElement : myListOfElements) {
            if (mySingleElement.getText().equals(loanType)) {
                mySingleElement.click();
                break;
            }
        }
    }

    @Then("^the customer receives the information text \"([^\"]*)\"$")
    public void theCustomerReceivesTheInformationText(final String text) throws Throwable {
        browser.waitForVisible(By.cssSelector("button.close"));
        assertThat(browser.findElement(By.cssSelector("p#tiptekst")).getText(), is(text));
    }

    @Given("^I want a car loan$")
    public void iWantACarLoan() throws Throwable {
        browser.get(System.getProperty("user.dir") + "\\src\\main\\resources\\index.html");
        browser.waitForVisible(By.cssSelector("input[value='Car-loan']"));
        browser.findElement(By.cssSelector("input[value='Car-loan']")).click();
        Thread.sleep(1000);
    }

    @When("^I loan the amount of \"(\\d+)\" euro$")
    public void iLoanTheAmountOfEuro(final Integer amount) throws Throwable {
        browser.findElement(By.cssSelector("input[name='amount']")).sendKeys(amount.toString());
        browser.findElement(By.cssSelector("button#verderPage1")).click();

        browser.waitForVisible(By.cssSelector("h2#toelichtingscherm"));
        browser.findElement(By.cssSelector("input#kennis_ja")).click();
        browser.findElement(By.cssSelector("button#verderPage2")).click();

        browser.waitForVisible(By.cssSelector("h2#persoonsgegevens"));
        final Faker faker = new Faker();
        browser.findElement(By.cssSelector("input#naam")).sendKeys(faker.superhero().name());
        if (faker.bool().bool()) {
            browser.findElement(By.cssSelector("label[for='man']")).click();
        } else {
            browser.findElement(By.cssSelector("label[for='vrouw']")).click();
        }

        //TODO check birthday
        final Date birthDay = faker.date().birthday(18, 65);
        final SimpleDateFormat dt1 = new SimpleDateFormat("dd-mm-yyyy");
        browser.findElement(By.cssSelector("input#geboortedatum")).sendKeys(dt1.format(birthDay));
        browser.findElement(By.cssSelector("input#adres")).sendKeys(faker.address().streetAddressNumber());
        browser.findElement(By.cssSelector("input#telefoonnummer")).sendKeys(faker.phoneNumber().phoneNumber());
        browser.findElement(By.cssSelector("input#rekeningnummer")).sendKeys(faker.number().digits(10));
        browser.findElement(By.cssSelector("input#inkomen")).sendKeys("" + faker.number().numberBetween(15000, 150000));
        browser.findElement(By.cssSelector("input#woonlasten")).sendKeys("" + faker.number().numberBetween(500, 2000));

        final int amountOfLivingSituationOptions = browser.findElements(By.cssSelector("select#woonsituatie option")).size();
        browser.findElement(By.cssSelector("select#woonsituatie option:nth-child(" + faker.number().numberBetween(2, amountOfLivingSituationOptions) + ")")).click();

        final int amountOfMaritialOptions = browser.findElements(By.cssSelector("select#staat option")).size();
        browser.findElement(By.cssSelector("select#staat option:nth-child(" + faker.number().numberBetween(2, amountOfMaritialOptions) + ")")).click();

        final int amountOfIncomeOptions = browser.findElements(By.cssSelector("select#typeinkomen option")).size();
        browser.findElement(By.cssSelector("select#typeinkomen option:nth-child(" + faker.number().numberBetween(2, amountOfIncomeOptions) + ")")).click();

        final String fileLocation = System.getProperty("user.dir") + "\\src\\test\\resources\\paymentslip.txt";
        browser.findElement(By.cssSelector("input#loonstrookje")).sendKeys(fileLocation);

        browser.findElement(By.cssSelector("button#verderPage3")).click();
        browser.waitForVisible(By.cssSelector("h2#ingevuldegegevens"));
    }

    @Then("^I can get it$")
    public void iCanGetIt() throws Throwable {

        assertThat(browser.findElement(By.cssSelector("h2#ingevuldegegevens")).getText(), is("Confirm data"));
    }
}