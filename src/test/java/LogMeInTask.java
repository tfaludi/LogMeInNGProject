import PageObjects.PageObjectsForTask;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LogMeInTask {

//1. Verify page title contains the “Selenium Easy string”
    @Test
    public void checkTitle() {

        // hardcoded value for the path of selenium driver need to work on this

        System.setProperty("webdriver.gecko.driver", "d:\\munka\\tools\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.seleniumeasy.com/test/");

        //checking title
        assertTrue(driver.getTitle().contains("Selenium Easy"));

        driver.quit();
    }

/*  2. From left side menu select Input Forms / Simple form demo
    In the Two Input Fields part fill value 5 for a and 6 for b then click on Get Total button
    Verify 11 is displayed as result*/
    @Test
    public void verifyAddInForm() {

        String firstDemoFormLocator = "a[href='./basic-first-form-demo.html']";

        // hardcoded value for the path of selenium driver need to work on this
        System.setProperty("webdriver.gecko.driver", "d:\\munka\\tools\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.seleniumeasy.com/test/");

        //opening header menu dropdown and forms tab
        PageObjectsForTask.headerMenuItems(driver).get(0).click();
        WebDriverWait waitForDropDown = new WebDriverWait(driver, 15);
        waitForDropDown.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(firstDemoFormLocator)));

        //clicking on form link
        PageObjectsForTask.simpleFormLink(driver).click();
        WebDriverWait waitForEnterATextbox = new WebDriverWait(driver, 15);

        //entering values to the textboxes
        waitForEnterATextbox.until(ExpectedConditions.visibilityOfElementLocated(By.id("sum1")));
        PageObjectsForTask.enterATextbox(driver).sendKeys("5");
        PageObjectsForTask.enterBTextbox(driver).sendKeys("6");
        PageObjectsForTask.getTotalButton(driver).click();

        //checking the total
        assertEquals(PageObjectsForTask.totalValue(driver).getText(),"11");
        driver.quit();
    }

//3. From Input Forms / Checkbox demo, in the Single Checkbox Demo part, select the single checkbox, then verify the Success message has appeared
    @Test
    public void verifyCheckboxSuccessMessage() {

        String basicCheckboxFormLocator = "a[href='./basic-first-form-demo.html']";
        String checkboxLocator = "isAgeSelected";

        // hardcoded value for the path of selenium driver need to work on this
        System.setProperty("webdriver.gecko.driver", "d:\\munka\\tools\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.seleniumeasy.com/test/");

        //opening header menu dropdown and forms tab
        PageObjectsForTask.headerMenuItems(driver).get(0).click();
        WebDriverWait waitForDropDown = new WebDriverWait(driver, 15);
        waitForDropDown.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(basicCheckboxFormLocator)));

        //clicking on form link
        PageObjectsForTask.checkBoxFormLink(driver).click();
        WebDriverWait waitForCheckbox = new WebDriverWait(driver, 15);
        waitForCheckbox.until(ExpectedConditions.visibilityOfElementLocated(By.id(checkboxLocator)));

        //clicking on checkbox
        PageObjectsForTask.singleCheckbox(driver).click();

        //checking success message
        assertEquals(PageObjectsForTask.successMessage(driver).getText(),"Success - Check box is checked");
        driver.quit();
    }

/*4. From Input Forms, Ajax Form submit: Verify when Name and Comment parts are left empty,
    then form can’t be submitted using the submit button.
    Then fill the input fields with some random string and click the submit button,
    then verify the form has been submitted successfully.    */
    @Test
    public void ajaxFormSubmitAndValidation() {

        String ajaxFormLocator = "a[href='./ajax-form-submit-demo.html']";
        String submitButtonLocator = "btn-submit";

        // hardcoded value for the path of selenium driver need to work on this
        System.setProperty("webdriver.gecko.driver", "d:\\munka\\tools\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.seleniumeasy.com/test/");

        //opening header menu dropdown and forms tab
        PageObjectsForTask.headerMenuItems(driver).get(0).click();
        WebDriverWait waitForDropDown = new WebDriverWait(driver, 15);
        waitForDropDown.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ajaxFormLocator)));

        //clicking on form link
        PageObjectsForTask.ajaxFormLink(driver).click();

        WebDriverWait waitForSubmitButton = new WebDriverWait(driver, 15);
        waitForSubmitButton.until(ExpectedConditions.visibilityOfElementLocated(By.id(submitButtonLocator)));

        //clicking on submit button without entering name
        PageObjectsForTask.submitButton(driver).click();

        //validation error checking
        if (PageObjectsForTask.nameTextbox(driver).getAttribute("style").contains("border: 1px solid rgb(255, 0, 0);")) {
            System.out.println("Validation worked");
        }
        else {
            System.out.println("Validation didnt work");
            Assert.assertFalse(PageObjectsForTask.nameTextbox(driver).getAttribute("style").contains("border: 1px solid rgb(255, 0, 0);"));
        }

        //entering random test for name and comment and submitting
        Random rand = new Random();
        PageObjectsForTask.nameTextbox(driver).sendKeys(generateString(rand, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 10));
        PageObjectsForTask.commentTextArea(driver).sendKeys(generateString(rand, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 50));
        PageObjectsForTask.submitButton(driver).click();

        //checking successful submit message
        WebDriverWait waitForSubmitSuccess = new WebDriverWait(driver, 15);
        waitForSubmitSuccess.until(ExpectedConditions.textToBePresentInElement(PageObjectsForTask.submitSuccess(driver),"Form submited Successfully!"));

        driver.quit();
    }

/*5. From Alerts and Modals / Bootstrap Alerts: Click on the first button (Autocloseable success message),
    then verify the “I’m an autocloseable success message. I will hide in 5 seconds.” message has appeared,
    then also detect when it has disappeared*/
    @Test
    public void alertsAndModalsAutoclose() {

        String alertMessageFormLocator = "a[href='./bootstrap-alert-messages-demo.html']";

        // hardcoded value for the path of selenium driver need to work on this
        System.setProperty("webdriver.gecko.driver", "d:\\munka\\tools\\geckodriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.seleniumeasy.com/test/");

        //opening header menu dropdown and forms tab
        PageObjectsForTask.headerMenuItems(driver).get(4).click();
        WebDriverWait waitForDropDown = new WebDriverWait(driver, 15);
        waitForDropDown.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(alertMessageFormLocator)));

        //clicking on form link
        PageObjectsForTask.alertFormLink(driver).click();

        //clicking on autocloseable button
        PageObjectsForTask.autoCloseableSuccess(driver).click();

        WebElement ImASubmitSuccess = driver.findElement(By.cssSelector("div[class='alert alert-success alert-autocloseable-success']"));
        WebDriverWait waitForIAmSubmitSuccess = new WebDriverWait(driver, 15);
        waitForIAmSubmitSuccess.until(ExpectedConditions.textToBePresentInElement(ImASubmitSuccess,"I'm an autocloseable success  message. I will hide in 5 seconds."));

        //driver.quit();
}

    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

}
